package com.university.yantra.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.university.yantra.BaseActivity;
import com.university.yantra.R;

public class PrivacyActivity extends BaseActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        textView=findViewById(R.id.textView26);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        String text = "Genie Fusion LLC registered office in Rohini, Delhi respect your privacy and are committed to protecting it through our compliance with this privacy policy. This policy describes: The types of information that we may collect from you when you access or use our websites, applications and other online services (collectively, our \"Services\"); and our practices for collecting, using, maintaining, protecting and disclosing that information. This policy applies only to information we collect through our Services, in email, text and other electronic communications sent through or in connection with our Services.<br>" +

                "        This policy DOES NOT apply to information that you provide to, or that is collected by, any third- party such as toy stores, toy designers, online toy stores at which you buy toys and/or pay through our Services and social networks that you use in connection with our Services. We encourage you to consult directly with such third-parties about their privacy practices." +
                "<br>" +
                "        <br>Please read this policy carefully to understand our policies and practices regarding your information and how we will treat it. If you do not agree with our policies and practices, your choice is not to use our Services. By accessing or using our Services, you agree to this privacy policy. This policy may change from time to time, your continued use of our Services after we make changes is deemed to be acceptance of those changes, so please check the policy periodically for updates.<br><br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>THE INFORMATION WE COLLECT AND HOW WE USE IT</b><br></font>" +
                "<br>" +
                "        We collect several types of information from and about users of our Services, including information: by which you may be personally identified; and/or about your internet connection, the equipment you use to access our Services and your usage details.<br>" +
                "                We collect this information: directly from you when you provide it to us; and/or automatically as you navigate through our Services (information collected automatically may include usage details, IP addresses and information collected through cookies, web beacons and other tracking technologies).<br>"+
                "        Information You Provide to us: The information we collect on or through our Services may include: Your account information: Your full name, email address, postal code, password and other information you may provide with your account, such as your gender, mobile phone number and website. Your profile picture that will be publicly displayed as part of your account profile. You may optionally provide us with this information through third-party sign-in services such as Facebook and Google Plus. In such cases, we fetch and store whatever information is made available to us by you through these sign-in services.<br>" +
                "<br>Your preferences: Your preferences and settings such as time zone and language.<br>" +
                "<br>Your searches and other activities: The search terms you have looked up and results you selected.<br><br> Your browsing information: How long you used our Services and which features you used; the ads you clicked on.<br><br>" +

                "" +
                "        Your communications: Communications between you and other users or merchants through our Services; your participation in a survey, poll, sweepstakes, contest or promotion scheme; your request for certain features (e.g., newsletters, updates or other products); your communication with us about employment opportunities posted to the services.<br>" +
                "<br>Your transactional information: If you make purchases through our Services, we may collect and store information about you to process your requests and automatically complete forms for future transactions, including (but not limited to) your phone number, address, email, billing information and credit or payment card information. This information may be shared with third-parties which assist in processing and fulfilling your requests, including PCI compliant payment gateway processors. When you submit credit or payment card information, we encrypt the information using industry standard technologies, as further described below under \"Payment Card Information.\"<br>" +
                "<br>Your Public Posts: You also may provide information (such as ratings, reviews, tips, photos, comments, likes, bookmarks, friends, lists, etc.) to be published or displayed (hereinafter, \"posted\") on publicly accessible areas of our Services, or transmitted to other users of our Services or third-parties (collectively, \"User Contributions\"). <br><br>" +
                "        Information About Your Friends: You have the option to request your friends to join the Services by providing their contact information. If you request a friend to join and connect with you on the Services, we will only use your friend’s contact information to process your request.<br><br>" +
                "        Information About Your Messages: If you exchange messages with others through the Services, we may store them to process and deliver them, allow you to manage them, and investigate possible violations of our Terms of Service and wrongdoing about the Services. If you send information from the Services to your mobile device via SMS text message, we may log your phone number, phone carrier, and the DateArrayResponse and time that the message was processed. Carriers may charge recipients for texts that they receive.<br><br>" +
                "        Information We Collect Through Automatic Data Collection Technologies: We may automatically collect certain information about the computer or devices (including mobile devices) you use to access the Services, and about your use of the Services, even if you use the Services without registering or logging in.<br><br>" +
                "        Usage information: Details of your use of our Services, including traffic data, location data, logs and other communication data and the resources that you access and use on or through our Services.<br><br>" +
                "        Computer and device information: Information about your computer, Internet connection and mobile device, including your IP address, operating systems, platforms, browser type, other browsing information (connection, speed, connection type etc.), device type, device’s unique device identifier, mobile network information and the device’s telephone number.<br><br>" +
                "        Stored information and files: Our applications also may access metadata and other information associated with other files stored on your mobile device. This may include, for example, photographs, audio and video clips, personal contacts and address book information.<br><br>" +
                "        Location information: Our applications collect real-time information about the location of your device, as permitted by you.<br><br>" +
                "        Your preferences: Your preferences and settings such as time zone and language.<br><br>" +
                "        Your activity on the Services: Information about your activity on the Services, such as your search queries, comments, domain names, search results selected, number of clicks, pages viewed and the order of those pages, how long you visited our Services, the DateArrayResponse and time you used the Services, error logs, and other similar information.<br><br>" +
                "        Mobile status: For mobile application users, the online or offline status of your application.<br>" +

                "        Cookies and Other Electronic Tools: We, and third parties with whom we partner, may use cookies, pixel tags, web beacons, mobile device IDs, “flash cookies” and similar files or technologies to collect and store information in respect to your use of the Services and third party websites.<br><br>" +
                "        Information from Third Parties: We may collect, process and store your user ID associated with any social media account (such as your Facebook and Google account) that you use to sign into the Services or connect with or use with the Services. When you sign in to your account with your social media account information, or otherwise connect to your social media account with the Services, you consent to our collection, storage, and use, in accordance with this Privacy Policy, of the information that you make available to us through the social media interface. This could include, without limitation, any information that you have made public through your social media account, information that the social media service shares with us, or information that is disclosed during the sign-in process. Please see your social media provider’s privacy policy and help center for more information about how they share information when you choose to connect your account.<br><br>" +
                "        Anonymous or De-Identified Data: We may anonymize and/or de-identify information collected from you through the Services or via other means, including via the use of third-party web analytic tools as described below. Thus, our use and disclosure of aggregated and/or de-identified information is not restricted by this Privacy Policy, and it may be used and disclosed to others without limitation.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>HOW WE USE THE INFORMATION WE COLLECT</b><br></font>" +
                "<br>" +
                "        We use the information we collect from and about you for a variety of purposes, including to:<br>" +
                "<br>" +
                "        o  Understand our users (what they do on our Services, what features they like, how they use them, etc.), improve the content and features of our Services (such as by personalizing content to your interests), process and complete your transactions, and make special offers.<br><br>" +
                "        o  Administer our Services and diagnose technical problems.<br><br>" +
                "        o  Send you communications that you have requested or that may be of interest to you.<br><br>" +
                "        o  Send you questions from other users that you may be able to answer if you have registered with Yantra. Enable us to show you ads that are relevant to you.<br><br>" +
                "        o  Generate and review reports and data about, and to conduct research on, our user base and Service usage patterns.<br><br>" +
                "        o  Administer contests and sweepstakes. Provide you with customer support.<br><br>" +
                "        o  Provide you with notices about your account.<br><br>" +
                "        o  Notify you about changes to our Services.<br><br>" +
                "        o  We may use the information we have collected from you to enable us to display advertisements to our advertisers’ target audiences. Even though we do not disclose your personal information for these purposes without your consent, if you click on or otherwise interact with an advertisement, the advertiser may assume that you meet its target criteria.<br><br>" +

                "•\t<font color=#cc0029><b>HOW WE SHARE THE INFORMATION WE COLLECT</b><br></font>" +
                "" +"<br>" +
                "        We may disclose personal information that we collect or you provide, as described in this privacy policy, in the following ways:<br>" +
                "<br>" +
                "       <font color=#cc0029> <b>General Information Disclosures</b><br></font>" +
                "<br>" +
                "        To our subsidiaries and affiliates, which are entities under common ownership or control of our company Genie Fusion LLC. To contractors, service providers and other third-parties whom we use to support our business and who are bound by contractual obligations to keep personal information confidential and use it only for the purposes for which we disclose it to them. To a buyer or other successor in the event of a merger, divestiture, restructuring, reorganization, dissolution or other sale or transfer of some or all of Genie Fusion LLC assets, whether as a going concern or as part of bankruptcy, liquidation or similar proceeding, in which personal information held by Genie Fusion LLC about the users of our Services are among the assets transferred. To third-parties to market their products or services to you if you have consented to receive the promotional updates. We contractually require these third-parties to keep personal information confidential and use it only for the purposes for which we disclose it to them.<br>" +
                "<br>" +
                "        To fulfill the purpose for which you provide it.<br>" +

                "        For any other purpose disclosed by us when you provide the information.<br>" +
                "<br>" +"       <font color=#cc0029><b>Service Providers</b><br></font>" +
                "" +"<br>" +
                "        We may share your information with outside vendors that we use for a variety of purposes, such as to send you emails and messages on behalf of other Yantra members, push notifications to your mobile device on our behalf, provide voice recognition services to process your spoken queries and questions, help us analyze use of our Services, and process and collect payments. Some of our products, services and databases are hosted by third party hosting services providers. We also may use vendors for other projects, such as conducting surveys or organizing sweepstakes for us. We may share information about you with these vendors only to enable them to perform their services.<br>" +
                "<br>" +
                "        <font color=#cc0029><b>Legal Purposes</b><br></font>" +
                "<br>" +
                "        We may share your information when we believe in good faith that such sharing is reasonably necessary to investigate, prevent, or act regarding possible illegal activities or to comply with legal process. We may also share your information to investigate and address threats or potential threats to the physical safety of any person, to investigate and address violations of this Privacy Policy or the Terms of Service, or to investigate and address violations of the rights of third parties and/or to protect the rights, property and safety of Genie Fusion LLC, our employees, users, or the public. This may involve the sharing of your information with law enforcement, government agencies, courts, and/or other organizations on account of legal request such as subpoena, court order or government demand to comply with the law.<br>" +
                "<br>" +
                "       <font color=#cc0029><b> Social Networks</b><br></font>" +
                "" +"<br>" +
                "        If you interact with social media features on our Services, such as the Facebook Like button, or use your social media credentials to log-in or post content, these features may collect information about your use of the Services, as well as post information about your activities on the social media service. Your interactions with social media companies are governed by their privacy policies.<br><br>" +
                "       <font color=#cc0029> <b>Payment Card Information</b><br></font>" +
                "" +"<br>" +
                "        To use certain of our Services, such as to make payments to certain toys, we may require credit or debit card account information. By submitting your credit or debit card account information through our Services, you expressly consent to the sharing of your information with toy stores, third-party payment processors, and other third-party service  providers (including  but not limited to  vendors who  provide fraud detection services to us and other third parties), and you further agree to the following terms: When you use a credit or debit card to secure a toy through our Sites, we provide your credit or debit card account information (including card number and expiration DateArrayResponse) to our third-party payment service providers and the applicable toy store.<br><br>" +
                "When you initially provide your credit or debit card account information through our Services to use our payment services, we provide your credit or debit card account information to our third-party payment service providers. As explained in our Terms of Use, these third parties may store your credit or debit card account information so you can use our payment services through our Services in the future.<br><br>" +
                "        For information about the security of your credit or debit card account information, see the “Security” section below.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>ANALYTICS AND TAILORED ADVERTISING</b><br></font>" +
                "" +"<br>" +
                "        To help us better understand your use of the Services, we may use third-party web analytics on our Services, such as Google Analytics. These service providers use the sort of technology described in the “Automatically-Collected Information” Section above. The information collected by this technology will be disclosed to or collected directly by these service providers, who use the information to evaluate our users’ use of the Services. We also use Google Analytics as described in the following section. To prevent Google Analytics from collecting or using your information, you may install the Google Analytics Opt-Out Browser Add-on.<br>" +
                "<br>" +
                "       <font color=#cc0029><b> Tailored Advertising</b><br></font>" +
                "" +"<br>" +
                "        Third parties whose products or services are accessible or advertised via the Services may also use cookies or similar technologies to collect information about your use of the Services. This is done to help them <br><br>(i) inform, optimize, and serve ads based on past visits to our website and other sites and <br><br>(ii) report how our ad impressions, other uses of ad services, and interactions with these ad impressions and ad services are related to visits to our website. We also allow other third parties (e.g., ad networks and ad servers such as Google Analytics, OpenX, PubMatic, DoubleClick and others) to serve tailored ads to you on the Services, and to access their own cookies or similar technologies on your computer, mobile phone, or other device you use to access the Services. We neither have access to, nor does this Privacy Policy govern, the use of cookies or other tracking technologies that may be placed by such third parties. These parties may permit you to opt out of ad targeting. If you are interested in more information about tailored browser advertising and how you can generally control cookies from being put on your computer to deliver tailored advertising (i.e., not just for the Services), the Digital Advertising Alliance’s Consumer Opt-Out Link to opt-out of receiving tailored advertising from companies that participate in those programs. To opt out of Google Analytics for Display Advertising or customize Google Display Network ads, you can visit the Google Ads Settings page. Please note that to the extent advertising technology is integrated into the Services, you may still receive ads even if you opt-out of tailored advertising. In that case, the ads will just not be tailored to your interests. Also, we do not control any of the above opt-out links and are not responsible for any choices you make using these mechanisms or the continued availability or accuracy of these mechanisms. When accessing the Services from a mobile application you may also receive tailored in- application advertisements. Each operating system: iOS, Android and Windows Phone provides its own instructions on how to prevent the delivery of tailored in-application advertisements. You may review the support materials and/or the privacy settings for the respective operating systems to opt-out of tailored in-application advertisements. For any other devices and/or operating systems, please visit the privacy settings for the applicable device or operating system or contact the applicable platform operator.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>CHOICES ABOUT HOW WE USE AND DISCLOSE YOUR INFORMATION</b><br></font>" +
                "" +"<br>" +
                "        We strive to provide you with choices regarding the personal information you provide to us. You can set your browser or mobile device to refuse all or some browser cookies, or to alert you when cookies are being sent. To learn how you can manage your Flash cookie settings, visit the Flash player settings page on Adobe’s website. If you disable or refuse cookies, please note that some parts of our Services may then be inaccessible or not function properly. We do not share your personal information with any advertising agency.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>COMMUNICATIONS CHOICES</b><br></font>" +
                "" +"<br>" +
                "        When you sign up for an account, you are opting in to receive emails from other Yantra users, businesses, and Yantra itself. You can log in to manage your email preferences here and you can follow the “unsubscribe” instructions in commercial email messages, but note that you cannot opt out of receiving certain administrative notices, service notices, or legal notices from Genie Fusion LLC.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>REVIEWING, CHANGING OR DELETING INFORMATION</b><br></font>" +
                "" +"<br>" +
                "        If you would like to review, change or delete personal information we have collected from you, or permanently delete your account, please use the “Contact Us” link at the bottom of every page (also located here), or contact the Genie Fusion LLC Privacy Officer.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>ACCESSING AND CORRECTING YOUR PERSONAL INFORMATION</b><br></font>" +
                "" +"<br>" +
                "        We will take reasonable steps to accurately record the personal information that you provide to us and any subsequent updates. We encourage you to review, update, and correct the personal information that we maintain about you, and you may request that we delete personal information about you that is inaccurate, incomplete, or irrelevant for legitimate purposes, or are being processed in a way which infringes any applicable legal requirement.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>SECURITY: HOW WE PROTECT YOUR INFORMATION</b><br></font>" +
                "" +"<br>" +
                "        We have implemented appropriate physical, electronic, and managerial procedures to safeguard and help prevent unauthorized access to your information and to maintain data security. These safeguards take into account the sensitivity of the information that we collect, process and store and the current state of technology. We follow generally accepted industry standards to protect the personal information submitted to us, both during transmission and once we receive it, including the use of vault and tokenization services from third party service providers. The third-party service providers with respect to our vault and tokenization services and our payment gateway and payment processing are all validated as compliant with the payment card industry standard (generally referred to as PCI compliant service providers). However, no method of transmission over the Internet or via mobile device, or method of electronic storage, is 100% secure. Therefore, while we strive to use commercially acceptable means to protect your personal information, we cannot guarantee its absolute security and thereby usage in a manner that is inconsistent with this Privacy Policy.<br>" +
                "<br>" +
                "        We assume no liability or responsibility for disclosure of your information due to errors in transmission, unauthorized third-party access, or other causes beyond our control. You play an important role in keeping your personal information secure. You should not share your user name, password, or other security information for your Yantra account with anyone. If we receive instructions using your user name and password, we will consider that you have authorized the instructions.<br>" +
                "<br>"+
                "•\t<font color=#cc0029><b>THIRD PARTY LINKS AND SERVICES</b><br></font>" +
                "" +
                "<br>The Services may contain links to third-party websites. Your use of these features may result in the collection, processing or sharing of information about you, depending on the feature. Please be aware that we are not responsible for the content or privacy practices of other websites or services which may be linked on our services. We do not endorse or make any representations about third-party websites or services. Our Privacy Policy does not cover the information you choose to provide to or that is collected by these third parties. We strongly encourage you to read such third parties’ privacy policies.<br>" +
                "<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>COMPLIANCE WITH DATA PROTECTION REGULATIONS</b><br></font>" +
                "" +"<br>" +
                "        The information we obtain from or about you may be processed and stored in India and our other various servers located across the globe, which may provide for different data protection rules than the country in which you reside. We comply with generally accepted industry standard regulations regarding the collection, use, and retention of data. Each location may provide for different data protection rules than the country in which you reside. By using the Services, you consent to the collection, transfer, use, storage and disclosure of your information as described in this Privacy Policy, including to the transfer of your information outside of your country of residence. If you have any questions relating to your personal data, please write to us.<br>" +
                "<br>" +
                "•\t   <font color=#cc0029><b>DATA RETENTION AND ACCOUNT TERMINATION</b><br></font>" +
                "" +"<br>" +
                "        You can close your account by visiting your profile settings page on our website. We will remove your public posts from view and/or dissociate them from your account profile, but we may retain information about you for the purposes authorized under this Privacy Policy unless prohibited by law. Thereafter, we will either delete your personal information or de-identify it so that it is anonymous and not attributed to your identity. For example, we may retain information to prevent, investigate, or identify possible wrongdoing about the Service or to comply with legal obligations.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>CHANGES TO THIS PRIVACY POLICY</b><br></font>" +
                "<br>" +
                "        We reserve the right to amend this Privacy Policy from time to time to reflect changes in the law, our data collection and use practices, the features of our services, or advances in technology. Please check this page periodically for changes. Use of information we collect is subject to the Privacy Policy in effect at the time such information is used. If we make any material changes to this Privacy Policy, we will post the changes here. Please review the changes carefully. Your continued use of the Services following the posting of changes to this Privacy Policy will constitute your consent and acceptance of those changes.<br>" +
                "<br>" +
                "•\t<font color=#cc0029><b>CONTACT US</b></font>" +
                "<br>" +"<br>" +
                "        If you have any queries relating to the processing/usage of information provided by you or Genie Fusion LLC Privacy Policy, you may email us<br>" +
                "<i>universityexperts85@gmail.com<i>"+
                "<br><br>For Users:" +
                "<br><b>Gaurav Gupta</b>" +
                "<br>Privacy Officer<br>";
        textView.setText(Html.fromHtml(text));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("About Us");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
