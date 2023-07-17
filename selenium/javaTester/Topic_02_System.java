package javaTester;

import java.io.File;

public class Topic_02_System {
	public static void main(String[] args) {
	String projectPath = System.getProperty("user.dir");
	System.out.println(projectPath);
	
	String osName = System.getProperty("os.name");
	System.out.println(osName);
	
	String dalatPhoto = "da_lat.jpeg";
	String dalatPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + dalatPhoto;
	System.out.println(dalatPhotoPath);
	
	}
}