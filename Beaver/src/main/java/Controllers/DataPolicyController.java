package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * <p> The Class DataPolicyController is the controller class of DataPolicy.fxml.
 * Initialize DataPolicy scene.
 *
 * @author Yichen Zhang
 * @version 1.0
 * @date 06/03/2021-15:01
 * @since 1.0
 */
public class DataPolicyController {
    /**
     * Text for containing title
     */
    @FXML
    TextArea area;
    /**
     * Data policy's text
     */
    String text = "\"Beaver collects the data you provide to us, your interactions with Beaver and your product usage data. Some of this data is provided directly by you, while others are collected by us through your interactions with the product and your use and experience with the product.  The data we collect depends on the environment in which you interact with Beaver, the choices you make, including your privacy Settings and the products and features you use. We also obtain data about you from third parties. You can make different choices about the technology you use and the data you share. When we ask you to provide personal data, you can refuse. Our products require you to provide certain personal data in order to provide services to you\"\n\n" +
            "\n\n" +
            "Personal data Beaver collects from you:\n\n" +
            "At Beaver, we believe that you can have great products and great privacy. This means that we strive to collect only the personal data that we need. The personal data Beaver collects depends on how you interact with Beaver.\n\n" +
            "When you create a Beaver ID, apply for commercial credit, purchase and/or activate a product or device, download a software update, connect to our services, contact us (including by social media), participate in an online survey, or otherwise interact with Beaver, we may collect a variety of information, including:\n\n" +
            "Account Information. Your Beaver ID and related account details, including email address, devices registered, account status, and age.\n\n" +
            "\n\n" +
            "* Device Information. Data from which your device could be identified, such as device serial number, or about your device, such as browser type\n\n" +
            "* Contact Information. Data such as name, email address, physical address, phone number, or other contact information\n\n" +
            "* Payment Information. Data about your billing address and method of payment, such as bank details, credit, debit, or other payment card information\n\n" +
            "* Fraud Prevention Information. Data used to help identify and prevent fraud, including a device trust score\n\n" +
            "* Usage Data. Data about your activity on and use of our offerings, such as app launches within our services, including browsing history; search history; product interaction; crash data, performance and other diagnostic data; and other usage data\n\n" +
            "* Financial Information. Details including salary, income, and assets information where collected, and information related to Beaver-branded financial offerings\n\n" +
            "* Other Information You Provide to Us. Details such as the content of your communications with Apple, including interactions with customer support and contacts through social media channels\n\n" +
            "\n\n" +
            "You are not required to provide the personal data that we have requested. However, if you choose not to do so, in many cases we will not be able to provide you with our products or services or respond to requests you may have.\n\n" +
            "Beaver’s use of personal data:\n\n" +
            "Beaver uses personal data to power our services, to process your transactions, to communicate with you, for security and fraud prevention, and to comply with law. We may also use personal data for other purposes with your consent. \n\n" +
            "Beaver uses your personal data only when we have a valid legal basis to do so. Depending on the circumstance, Beaver may rely on your consent or the fact that the processing is necessary to fulfill a contract with you, protect your vital interests or those of other persons, or to comply with law. We may also process your personal data where we believe it is in our or others' legitimate interests, taking into consideration your interests, rights, and expectations.\n\n" +
            "\n\n" +
            "* Power Our Services. Beaver collects personal data necessary to power our services, which may include personal data collected to personalize or improve our offerings, for internal purposes such as auditing or data analysis, or for troubleshooting. \n\n" +
            "* Process Your Transactions. To process transactions, Beaver must collect data such as your name, purchase, and payment information.\n\n" +
            "* Communicate with You. To respond to communications, reach out to you about your transactions or account, market our products and services, provide other relevant information, or request information or feedback. From time to time, we may use your personal data to send important notices, such as communications about purchases and changes to our terms, conditions, and policies. Because this information is important to your interaction with Beaver, you may not opt out of receiving these important notices. \n\n" +
            "* Security and Fraud Prevention. To protect individuals, employees, and Beaver and for loss prevention and to prevent fraud, including to protect individuals, employees, and Beaver for the benefit of all our users, and prescreening or scanning uploaded content for potentially illegal content, including child sexual exploitation material.\n\n" +
            "* Comply with Law. To comply with applicable law - for example, to satisfy tax or reporting obligations, or to comply with a lawful governmental request.\n\n" +
            "\n\n" +
            "Beaver does not use algorithms or profiling to make any decision that would significantly affect you without the opportunity for human review.\n\n" +
            "Beaver retains personal data only for so long as necessary to fulfill the purposes for which it was collected, including as described in this Privacy Policy or in our service-specific privacy notices, or as required by law. We will retain your personal data for the period necessary to fulfill the purposes outlined in this Privacy Policy and our service-specific privacy summaries. When assessing retention periods, we first carefully examine whether it is necessary to retain the personal data collected and, if retention is required, work to retain the personal data for the shortest possible period permissible under law.";

    /**
     * initialize
     * <p>
     * initialize the text area
     *
     * @author Ruibin CHEN
     * @date 2021/3/16- 04:06
     */
    @FXML
    private void initialize() {
        area.setText(text);
    }
}
