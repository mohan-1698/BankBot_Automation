package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Project path + screenshots folder
        String folderPath = System.getProperty("user.dir") + "/screenshots/";

        // Create folder if not exists
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Final file path
        String filePath = folderPath + testName + "_" + timestamp + ".png";

        try {
            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(filePath);
            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved at: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }
}