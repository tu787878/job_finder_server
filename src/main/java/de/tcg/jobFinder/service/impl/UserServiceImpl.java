package de.tcg.jobFinder.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.tcg.jobFinder.dto.PathId;
import de.tcg.jobFinder.entity.Account;
import de.tcg.jobFinder.entity.AccountToken;
import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.reposity.AccountReposity;
import de.tcg.jobFinder.reposity.UserReposity;
import de.tcg.jobFinder.service.AccountTokenService;
import de.tcg.jobFinder.service.MyUserDetailsService;
import de.tcg.jobFinder.service.UserService;

@Service
public class UserServiceImpl extends UntilService implements UserService {

	@Autowired
	UserReposity userReposity;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private AccountTokenService accountTokenService;

	@Autowired
	private AccountReposity accountReposity;

	@Value("${media.path}")
	String basisPath;

	@Override
	public List<User> getUsers() {

		return userReposity.findAll();
	}

	@Override
	public User getUserById(String userId) {
		User user = userReposity.findByUserId(userId);
		return user;
	}

	@Override
	public boolean updateUserById(HttpServletRequest request, User user, String image) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				String userId = account.getUserId();
				if (!account.isBusiness() && userId.equals(user.getUserId())) {

					if (image != null) {
						// save logo
						String pathString = basisPath + "/image" + PathId.upload.getPath();

						byte[] data = Base64.getDecoder().decode(image.split(",")[1].getBytes(StandardCharsets.UTF_8));
						OutputStream stream;

						String imageName = "/user_" + user.getUserId().split("_")[1] + ".png";

						try {
							stream = new FileOutputStream(pathString + imageName);
							stream.write(data);
						} catch (IOException e) {
							e.printStackTrace();
						}

						String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build()
								.toUriString();

						user.setAvatar(baseUrl + "/media/image/upload" + imageName);
					}

					userReposity.save(user);
					return true;
				}
			}

		}
		return false;

	}

	@Override
	public Account createUser(HttpServletRequest request, User user, String image) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());

				if (!account.isBusiness()) {
					if (account.getUserId().equals("")) {
						String userId = "";
						int count = 0;
						boolean flag = true;
						do {
							userId = "USER_" + account.getUserName().split("@")[0] + ((count != 0) ? count : "");
							flag = userReposity.existsByUserId(userId);
							count++;
						} while (flag);

						if (image != null) {
							// save logo
							String pathString = basisPath + "/image" + PathId.upload.getPath();

							byte[] data = Base64.getDecoder()
									.decode(image.split(",")[1].getBytes(StandardCharsets.UTF_8));
							OutputStream stream;

							String imageName = "/user_" + userId.split("_")[1] + ".png";

							try {
								stream = new FileOutputStream(pathString + imageName);
								stream.write(data);
							} catch (IOException e) {
								e.printStackTrace();
							}

							String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
									.build().toUriString();

							user.setAvatar(baseUrl + "/media/image/upload" + imageName);
						} else {
							String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
									.build().toUriString();
							if (user.getGender() == 1) {
								user.setAvatar(baseUrl + "/media/image/sample" + "/avatar01.png");
							} else {
								user.setAvatar(baseUrl + "/media/image/sample" + "/avatar02.png");
							}

						}

						user.setUserId(userId);
						userReposity.save(user);
						account.setUserId(userId);
						accountReposity.save(account);

						return account;
					} else {
						boolean check = userReposity.existsByUserId(account.getUserId());
						if (!check) {
							String userId = "";
							int count = 0;
							boolean flag = true;
							do {
								userId = "USER_" + account.getUserName().split("@")[0] + ((count != 0) ? count : "");
								flag = userReposity.existsByUserId(userId);
								count++;
							} while (flag);

							if (image != null) {
								// save logo
								String pathString = basisPath + "/image" + PathId.upload.getPath();

								byte[] data = Base64.getDecoder()
										.decode(image.split(",")[1].getBytes(StandardCharsets.UTF_8));
								OutputStream stream;

								String imageName = "/user_" + userId.split("_")[1] + ".png";

								try {
									stream = new FileOutputStream(pathString + imageName);
									stream.write(data);
								} catch (IOException e) {
									e.printStackTrace();
								}

								String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
										.build().toUriString();

								user.setAvatar(baseUrl + "/media/image/upload" + imageName);
							} else {
								String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)
										.build().toUriString();
								user.setAvatar(baseUrl + "/media/image/sample" + "/user.png");
							}

							user.setUserId(userId);
							userReposity.save(user);
							account.setUserId(userId);
							accountReposity.save(account);

							return account;
						}
					}
				}
			}

		}
		return null;
	}

	@Override
	public boolean deleteUser(HttpServletRequest request, String userId) {
		String token = toToken(request);
		if (token != null) {
			AccountToken accountToken = accountTokenService.getAccountTokenByAccessToken(token);
			if (accountToken != null && accountToken.isActive()) {
				Account account = myUserDetailsService.getAccountByAccountId(accountToken.getAccountId());
				if (!account.isBusiness() && userId.equals(account.getUserId())) {
					User user = userReposity.findByUserId(userId);
					userReposity.delete(user);
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public String getEmailUserById(String userId) {
		Account account = accountReposity.findByUserId(userId);
		if(account != null) return account.getUserName();
		return null;
	}

}
