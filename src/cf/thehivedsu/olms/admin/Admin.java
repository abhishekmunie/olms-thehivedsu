package cf.thehivedsu.olms.admin;

import cf.thehivedsu.olms.bean.CredentialBean;

public class Admin {

	public static boolean authenticate(CredentialBean credential) {
		return credential.getUsername().equals(System.getenv("ADMIN_SIGNIN_ID"))
				&& credential.getPassword().equals(System.getenv("ADMIN_SIGNIN_PASSWORD"));
	}

}
