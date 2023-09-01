package com.tc.training.cabrentals.constants;

public interface EmailTemplateConstants {
  String EmailVerificationTemplate = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Email Verification - OneCar</title>
            <style>
                /* Add your email styles here */
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f2f2f2;
                    margin: 0;
                    padding: 0;
                    text-align: center;
                }
                .container {
                    max-width: 600px;
                    margin: 0 auto;
                    padding: 20px;
                    background-color: #ffffff;
                    border-radius: 5px;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                }
                .logo {
                    margin-bottom: 20px;
                }
                .verification-text {
                    font-size: 18px;
                    margin-bottom: 30px;
                }
                .btn-verify {
                    display: inline-block;
                    padding: 10px 20px;
                    background-color: #007BFF;
                    color: #ffffff;
                    text-decoration: none;
                    border-radius: 4px;
                    font-weight: bold;
                }
                .footer {
                    margin-top: 30px;
                    color: #777777;
                    font-size: 12px;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <img class="logo" src="https://yourwebsite.com/logo.png" alt="OneCar Logo">
                <p class="verification-text">Hi %s,</p>
                <p>Your OneCar account is almost ready to go! Please click the button below to verify your email address and start enjoying our car rental services:</p>
                <a class="btn-verify" href="%s">Verify Email</a>
                <p class="footer">If you didn't request this verification, you can safely ignore this email. For any questions or assistance, please contact our support team at support@onecar.com.</p>
            </div>
        </body>
        </html>
      """;
}
