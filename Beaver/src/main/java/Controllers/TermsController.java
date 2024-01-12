package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * <p> The Class TermsController is the controller class of Terms.fxml.
 * Initialize UserInform scene.
 *
 * @author Yichen Zhang
 * @version 1.0
 * @date
 * @since 1.0
 */
public class TermsController {
    /**
     * Text area for containing title.
     */
    @FXML
    TextArea area;

    /**
     * Terms' text.
     */
    String text = "TERMS OF SERVICE\n\n" +
            "ANY DISPUTE BETWEEN YOU AND US, EXCEPT FOR SMALL CLAIMS, IS SUBJECT TO A CLASS ACTION WAIVER AND MUST BE RESOLVED BY INDIVIDUAL BINDING ARBITRATION. PLEASE READ THIS AGREEMENT IN ITS ENTIRETY, INCLUDING THE ARBITRATION PROVISION BELOW. \n\n" +
            "NOTHING IN THESE TERMS IS INTENDED TO AFFECT YOUR RIGHTS UNDER THE LAW IN YOUR USUAL PLACE OF RESIDENCE THAT CAN NOT BE ALTERED BY THESE TERMS. IF THERE IS A CONFLICT BETWEEN THOSE RIGHTS AND THESE TERMS, YOUR RIGHTS UNDER APPLICABLE LOCAL LAW WILL PREVAIL. \n\n" +
            "1. Contract between You and Us \n\n" +
            "You must read and agree to these terms (the \"Agreement\") before using the Beaver Services. If you do not agree, you may not use the Beaver Services. These terms describe the limited basis on which the Beaver Services are made available to you and supersede prior agreements or arrangements. \n\n" +
            "Supplemental terms and conditions may apply to some Beaver Services, such as rules for a particular competition, content, software, application, service or other activity, or terms that may accompany certain territories, content, products or software accessible through the Beaver Services. If applicable, such supplemental terms and conditions will be disclosed to you in connection with such competition, content, product, software, application, service or activity. Any supplemental terms and conditions are in addition to these terms and, in the event of a conflict, the supplemental terms will prevail over these terms. If you do not agree to the supplemental terms and conditions disclosed, you must not use the applicable Beaver Service. \n\n" +
            "We may change these terms at any time. Any such amendment will be effective thirty (30) days following either our dispatch of a notice to you or our posting of the amended terms. You are responsible for periodically reviewing the terms for updates and amendments. By continuing to use the Beaver Services you will be deemed to have agreed to and accepted any amendments. If you do not agree to any change to these terms, you must discontinue using the Beaver Services. Our customer service representatives are not authorized to modify any provision of these terms, either verbally or in writing. \n\n" +
            "We may immediately terminate this contract with respect to you (including your access to the Beaver Services) if you fail to comply with any provision of these terms. \n\n" +
            "\n\n" +
            "\n\n" +
            "2. The Beaver Services \n\n" +
            "The Beaver Services are for your personal use. They do not constitute legal, medical or healthcare advice or diagnosis and cannot be used for such purposes. \n\n" +
            "The Beaver Services are our copyrighted property or the copyrighted property of our licensors or licensees and all trademarks, service marks, trade names, trade dress and other intellectual property rights in the Beaver Services are owned by us or our licensors or licensees. Except as we specifically agree in writing, no element of the Beaver Services may be used or exploited in any way other than as part of the Beaver Services offered to you. You may own the physical media on which elements of the Beaver Services are delivered to you, but we retain full and complete ownership of the Beaver Services. We do not transfer title to any portion of the Beaver Services to you. \n\n" +
            "\n\n" +
            "\n\n" +
            "3. Your Content and Account \n\n" +
            "Accounts \n\n" +
            "Beaver Services permit or require you to create an account to participate or to secure additional benefits. You agree that any information you provide and maintain is accurate, current and complete, including your contact information for notices and other communications from us and your payment information. You agree not to impersonate or misrepresent your affiliation with any person or entity, including using another person’s username, password or other account information, or another person’s name or likeness, or provide false details for a parent or guardian. You agree that we may take steps to verify the accuracy of information you provide, including contact information for a parent or guardian. \n\n" +
            "We have adopted and implemented a policy that provides for the termination, in appropriate circumstances, of the accounts of users who are repeat infringers of copyright. In addition, we may suspend or terminate your account and your ability to use the Beaver Services if you engage in, encourage, or advocate for illegal conduct or if you fail to comply with these terms or any supplemental terms. \n\n" +
            "\n\n" +
            "Passwords and Security \n\n" +
            "You agree that you will not share your account or account information with others. You are responsible for taking reasonable steps to maintain the confidentiality of your username and password, and you are responsible for all activities under your account that you can reasonably control. You agree to promptly notify us of any unauthorized use of your username, password or other account information, or of any other breach of security that you become aware of involving your account or the Beaver Services. \n\n" +
            "The security, integrity and confidentiality of your information are extremely important to us. We have implemented technical, administrative and physical security measures that are designed to protect your information from unauthorized access, disclosure, use and modification.";

    /**
     * initialize
     * <p>
     * Initialize the controller
     *
     * @author Ruibin CHEN
     * @date 2021/3/16- 04:50
     */
    @FXML
    private void initialize() {
        area.setText(text);
    }

}
