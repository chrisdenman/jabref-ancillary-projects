package org.jabref;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class Main {

    void drive() {

        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();

        // Disables built-in PDF viewer so file is downloaded
//        profile.setPreference("pdfjs.disabled", true);
//        options.setProfile(profile);

        // Decorate WebDriver with an event logger
        WebDriverListener listener = new WebDriverListener() {
            @Override
            public void beforeAnyCall(Object target, Method method, Object[] args) {
                System.out.printf("target=\"%s\", method=\"%s\", args=%s\n", target, method, Arrays.toString(args));
            }
        };
        final WebDriver webDriver = new FirefoxDriver(options);
        WebDriver decorated = new EventFiringDecorator<>(listener).decorate(webDriver);

        // Navigate to a CloudFlare protected PDF
        decorated.navigate().to("https://journals.aps.org/prl/pdf/10.1103/PhysRevLett.116.061102");

        // Sleep for 1s (give the PDF time to 'settle') - may not be necessary
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // Print the text displayed by the PDF viewer
        System.out.println(decorated.findElement(By.id("viewer")).getText());

        webDriver.quit();
    }

    public static void main(String[] args) {
        new Main().drive();
    }
}
