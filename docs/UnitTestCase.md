

### Test Case

##### Main Test

##### Function: SetRoot()

| Test Case ID | Date  | Test Scenario                                        | Test Data        | Expected Results                       | Actual Results                         | P/F  | Comments                               |
| ------------ | ----- | ---------------------------------------------------- | ---------------- | -------------------------------------- | -------------------------------------- | ---- | -------------------------------------- |
| 1            | 03/07 | Call the SetRoot function,  jump to SetPassword page | SetPassword.fxml | Main Scene changed to SetPassword page | Main Scene changed to SetPassword page | P    | The Stage Root is changed successfully |

##### Function: loadFXML()

| Test Case ID | Date  | Test Scenario                                                | Test Data   | Expected Results                  | Actual Results                    | P/F  | Comments                               |
| ------------ | ----- | ------------------------------------------------------------ | ----------- | --------------------------------- | --------------------------------- | ---- | -------------------------------------- |
| 1            | 03/07 | Call the loadFXML function to get the SignUp page, use the return value to set the scene | SignUp.fxml | Main Scene changed to SignUp page | Main Scene changed to SignUp page | P    | The Stage Root is changed successfully |

------



##### FilterController Test

##### Function: AddCondition()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                     | Expected Results                         | Actual Results                           | P/F  | Comments                   |
| ------------ | ----- | ------------------------------------------------------------ | --------------------------------------------- | ---------------------------------------- | ---------------------------------------- | ---- | -------------------------- |
| 1            | 03/07 | Click the AddCondition button to check whether the condition is added | clickOn(Main.scene.lookup("#AddConditionBt")) | There is a new HBox in the Condition Box | There is a new HBox in the Condition Box | P    | Add Condition successfully |

##### Function: Confirm()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                | Expected Results                 | Actual Results                   | P/F  | Comments                    |
| ------------ | ----- | ------------------------------------------------------------ | ---------------------------------------- | -------------------------------- | -------------------------------- | ---- | --------------------------- |
| 1            | 03/07 | Click the ConfirmBt to check if the SQL statement is invalid, then there is a warning dialog | clickOn(Main.scene.lookup("#ConfirmBt")) | There is a warning dialog prompt | There is a warning dialog prompt | P    | Confirm invoke successfully |

##### Function: Preview()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                | Expected Results                     | Actual Results                       | P/F  | Comments                    |
| ------------ | ----- | ------------------------------------------------------------ | ---------------------------------------- | ------------------------------------ | ------------------------------------ | ---- | --------------------------- |
| 1            | 03/07 | Click the PreviewBt to show the SQL statement which has been set | clickOn(Main.scene.lookup("#PreviewBt")) | The preview label content is changed | The preview label content is changed | P    | Preview invoke successfully |

##### Function: IsDouble()

| Test Case ID | Date  | Test Scenario                                     | Test Data | Expected Results | Actual Results | P/F  | Comments                                    |
| ------------ | ----- | ------------------------------------------------- | --------- | ---------------- | -------------- | ---- | ------------------------------------------- |
| 1            | 03/07 | Use different string data to test isDouble method | "11.22"   | True             | True           | P    | "11.22" is a double number in string format |
| 2            | 03/07 | Use different string data to test isDouble method | "a"       | False            | False          | P    | "a" is not a double number in string format |

------



##### FTDController Test

##### Function: ChooseOnClick()

| Test Case ID | Date  | Test Scenario                                                | Test Data                             | Expected Results                | Actual Results                  | P/F  | Comments                            |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------- | ------------------------------- | ------------------------------- | ---- | ----------------------------------- |
| 1            | 03/07 | Click the choose button to check whether it can open a file chooser | clickOn(Main.scene.lookup("#choose")) | There is a  file chooser opened | There is a  file chooser opened | P    | File chooser is opened successfully |

##### Function: ConfirmOnClick()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                 | Actual Results                   | P/F  | Comments                            |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | -------------------------------- | -------------------------------- | ---- | ----------------------------------- |
| 1            | 03/07 | Set the new name and click the confirm button to check whether the file name is changed | textField.setText("TestFile") clickOn(Main.scene.lookup("#confirm")) | File name is changed to TestFile | File name is changed to TestFile | P    | File name is confirmed successfully |

##### Function: ImportButtonOnClick()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                   | Expected Results        | Actual Results          | P/F  | Comments                             |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------- | ----------------------- | ----------------------- | ---- | ------------------------------------ |
| 1            | 03/07 | Click the import button to check whether the file is uploaded to database | clickOn(Main.scene.lookup("#importButton")) | Uploading process start | Uploading process start | P    | Import method is invoke successfully |

------



##### MainMenuController Test

##### Function: RunModel()

| Test Case ID | Date  | Test Scenario                                                | Test Data                             | Expected Results        | Actual Results          | P/F  | Comments                                     |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------- | ----------------------- | ----------------------- | ---- | -------------------------------------------- |
| 1            | 03/07 | Click the run button to check whether the run process is begin | clickOn(Main.scene.lookup("#runBtn")) | The process bar changed | The process bar changed | P    | Model Application process start successfully |

------



##### ShowDataController Test

##### Function: nextPage()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                 | Expected Results              | Actual Results                | P/F  | Comments                                   |
| ------------ | ----- | ------------------------------------------------------------ | ----------------------------------------- | ----------------------------- | ----------------------------- | ---- | ------------------------------------------ |
| 1            | 03/07 | Click the nextPage button to check whether the table go to the next page | clickOn(Main.scene.lookup("#nextPageBt")) | The table go to the next page | The table go to the next page | P    | The table go to the next page successfully |

##### Function: lastPage()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                 | Expected Results              | Actual Results                | P/F  | Comments                                   |
| ------------ | ----- | ------------------------------------------------------------ | ----------------------------------------- | ----------------------------- | ----------------------------- | ---- | ------------------------------------------ |
| 1            | 03/07 | Click the lastPage button to check whether the table go to the next page | clickOn(Main.scene.lookup("#lastPageBt")) | The table go to the last page | The table go to the last page | P    | The table go to the last page successfully |

------



##### DataOperator Test

##### Function: setFileName()

| Test Case ID | Date  | Test Scenario                                             | Test Data  | Expected Results | Actual Results | P/F  | Comments                          |
| ------------ | ----- | --------------------------------------------------------- | ---------- | ---------------- | -------------- | ---- | --------------------------------- |
| 1            | 03/07 | Set the FileName and check whether it is set successfully | "New File" | New File         | New File       | P    | The File Name is set successfully |

------



##### TreeItemCollection Test

##### Function: getModelsList()

| Test Case ID | Date  | Test Scenario                        | Test Data                                | Expected Results | Actual Results | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------ | ---------------------------------------- | ---------------- | -------------- | ---- | ------------------------ |
| 1            | 03/07 | Instantiate a new TreeItemCollection | treeItemColletion.getModelsList().size() | 2                | 2              | P    | The ModelsList size is 2 |

##### Function: getDataSetsList()

| Test Case ID | Date  | Test Scenario                        | Test Data                                  | Expected Results | Actual Results | P/F  | Comments                   |
| ------------ | ----- | ------------------------------------ | ------------------------------------------ | ---------------- | -------------- | ---- | -------------------------- |
| 1            | 03/07 | Instantiate a new TreeItemCollection | treeItemColletion.getDataSetsList().size() | 3                | 3              | P    | The DataSetsList size is 2 |

##### Function: getScenariosList()

| Test Case ID | Date  | Test Scenario                        | Test Data                                   | Expected Results | Actual Results | P/F  | Comments                    |
| ------------ | ----- | ------------------------------------ | ------------------------------------------- | ---------------- | -------------- | ---- | --------------------------- |
| 1            | 03/07 | Instantiate a new TreeItemCollection | treeItemColletion.getScenariosList().size() | 3                | 3              | P    | The ScenariosList size is 2 |

##### Function: getRecordsList()

| Test Case ID | Date  | Test Scenario                        | Test Data                                 | Expected Results | Actual Results | P/F  | Comments                  |
| ------------ | ----- | ------------------------------------ | ----------------------------------------- | ---------------- | -------------- | ---- | ------------------------- |
| 1            | 03/07 | Instantiate a new TreeItemCollection | treeItemColletion.getRecordsList().size() | 2                | 2              | P    | The RecordsList size is 2 |

------



##### TreeItemObject Test

##### Function: getItemType()

| Test Case ID | Date  | Test Scenario                                | Test Data                              | Expected Results | Actual Results | P/F  | Comments               |
| ------------ | ----- | -------------------------------------------- | -------------------------------------- | ---------------- | -------------- | ---- | ---------------------- |
| 1            | 03/07 | Instantiate a new TreeItemObject for testing | TreeItemObject(1, false, "ABC", "DEF") | 1                | 1              | P    | The TreeItem Type is 1 |

##### Function: getItemFavorite(

| Test Case ID | Date  | Test Scenario                                | Test Data                              | Expected Results | Actual Results | P/F  | Comments                    |
| ------------ | ----- | -------------------------------------------- | -------------------------------------- | ---------------- | -------------- | ---- | --------------------------- |
| 1            | 03/07 | Instantiate a new TreeItemObject for testing | TreeItemObject(1, false, "ABC", "DEF") | false            | false          | P    | The TreeItem favor is false |

##### Function: getItemName()

| Test Case ID | Date  | Test Scenario                                | Test Data                              | Expected Results | Actual Results | P/F  | Comments                 |
| ------------ | ----- | -------------------------------------------- | -------------------------------------- | ---------------- | -------------- | ---- | ------------------------ |
| 1            | 03/07 | Instantiate a new TreeItemObject for testing | TreeItemObject(1, false, "ABC", "DEF") | ABC              | ABC            | P    | The TreeItem name is ABC |

##### Function: getItemValue()

| Test Case ID | Date  | Test Scenario                                | Test Data                              | Expected Results | Actual Results | P/F  | Comments                  |
| ------------ | ----- | -------------------------------------------- | -------------------------------------- | ---------------- | -------------- | ---- | ------------------------- |
| 1            | 03/07 | Instantiate a new TreeItemObject for testing | TreeItemObject(1, false, "ABC", "DEF") | DEF              | DEF            | P    | The TreeItem value is DEF |

##### Function: setItemType()

| Test Case ID | Date  | Test Scenario                                           | Test Data                     | Expected Results | Actual Results | P/F  | Comments               |
| ------------ | ----- | ------------------------------------------------------- | ----------------------------- | ---------------- | -------------- | ---- | ---------------------- |
| 1            | 03/07 | Set the TreeItem type to 2, then test the TreeItem type | treeItemObject.setItemType(2) | 2                | 2              | P    | The TreeItem type is 2 |

##### Function: setItemFavorite()

| Test Case ID | Date  | Test Scenario                                                | Test Data                            | Expected Results | Actual Results | P/F  | Comments                   |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------ | ---------------- | -------------- | ---- | -------------------------- |
| 1            | 03/07 | Set the TreeItem favor to true, then test the TreeItem favor | treeItemObject.setItemFavorite(true) | true             | true           | P    | The TreeItem favor is true |

##### Function: setItemName()

| Test Case ID | Date  | Test Scenario                                               | Test Data                         | Expected Results | Actual Results | P/F  | Comments                 |
| ------------ | ----- | ----------------------------------------------------------- | --------------------------------- | ---------------- | -------------- | ---- | ------------------------ |
| 1            | 03/07 | Set the TreeItem name to "AAA", then test the TreeItem name | treeItemObject.setItemName("AAA") | "AAA"            | "AAA"          | P    | The TreeItem name is AAA |

##### Function: setItemValue()

| Test Case ID | Date  | Test Scenario                                                | Test Data                          | Expected Results | Actual Results | P/F  | Comments                  |
| ------------ | ----- | ------------------------------------------------------------ | ---------------------------------- | ---------------- | -------------- | ---- | ------------------------- |
| 1            | 03/07 | Set the TreeItem value to "QWE", then test the TreeItem value | treeItemObject.setItemValue("QWE") | "QWE"            | "QWE"          | P    | The TreeItem value is QWE |

------

##### MergeController Test

##### Function: addComparison()

| Test Case ID | Date  | Test Scenario                                                | Test Data                              | Expected Results          | Actual Results            | P/F  | Comments                        |
| ------------ | ----- | ------------------------------------------------------------ | -------------------------------------- | ------------------------- | ------------------------- | ---- | ------------------------------- |
| 1            | 03/07 | Click add button to check whether add comparison successfully | clickOn(Main.scene.lookup("#AddBtn")); | A new comparison is added | A new comparison is added | P    | Add new comparison successfully |

##### Function: showSymbols()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                       | Expected Results | Actual Results   | P/F  | Comments                                |
| ------------ | ----- | ------------------------------------------------------------ | ----------------------------------------------- | ---------------- | ---------------- | ---- | --------------------------------------- |
| 1            | 03/07 | Click the check box to set whether show the line chart symbols | clickOn(Main.scene.lookup("#ShowSymbolCheck")); | Hide the symbols | Hide the symbols | P    | Successfully set the line chart symbols |

##### 

------



##### SignInController Test

##### Function: inputVerify()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                                             | Actual Results                                               | P/F  | Comments                                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------------------- |
| 1            | 03/07 | Set the input fields empty and check whether the error display work successfully | TextField textField = (TextField) Main.scene.lookup("#username"); PasswordField passwordField = (PasswordField) Main.scene.lookup("#password"); clickOn(textField); clickOn(passwordField); clickOn(textField); | "Username or Email address is empty!" and "Password is empty!" popped out | "Username or Email address is empty!" and "Password is empty!" popped out | P    | The verification part works successfully |
| 2            | 03/07 | Set the username fields with incorrect format and check whether the error display work successfully | textField.setText("Danny123"); clickOn(passwordField); passwordField.setText("!Aa112224"); | "Username or Email address format is incorrect!" popped out  | "Username or Email address format is incorrect!" popped out  | P    | The verification part works successfully |
| 3            | 03/07 | Set the password fields with incorrect format and check whether the error display work successfully | clickOn(textField); textField.setText("Danny"); clickOn(passwordField); passwordField.setText("112233"); clickOn(textField); | "Password format is incorrect!" popped out                   | "Password format is incorrect!" popped out                   | P    | The verification part works successfully |
| 4            | 03/07 | Set correct format and check whether the error display is hidden | clickOn(passwordField); passwordField.setText("!Aa112224"); clickOn(textField); | The notification  is hidden                                  | The notification  is hidden                                  | P    | The verification part works successfully |

##### Function: signIn()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                                             | Actual Results                                               | P/F  | Comments                                    |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------- |
| 1            | 03/07 | Test the situations of   no network connections, whether the error message displayed | TextField textField = (TextField) Main.scene.lookup("#username");   PasswordField passwordField = (PasswordField) Main.scene.lookup("#password"); textField.setText("Danny"); passwordField.setText("!Aa112224"); clickOn(signInController.SignInBtn); Platform.runLater(() -> {   signInController.connectWarning.setVisible(true); }); | "Can't access the database, please check your network" popped out | "Can't access the database, please check your network" popped out | P    | Correct notification displayed successfully |
| 2            | 03/07 | Set wrong password or username and check whether the error display work successfully when clicking sign in button | clickOn(passwordField); passwordField.setText("!Aa11224"); clickOn(signInController.SignInBtn); Platform.runLater(() -> {}); | "User does not exist or password is incorrect!" popped out   | "User does not exist or password is incorrect!" popped out   | P    | Correct notification displayed successfully |
| 3            | 03/07 | Test whether user can sign in successfully with correct user information | clickOn(passwordField); passwordField.setText("!Aa112224"); clickOn(signInController.SignInBtn); | An alert of sign in successfully popped out and then turn to the main menu page | An alert of sign in successfully popped out and then turn to the main menu page | P    | Sign in and page jump is succeed            |

##### Function: jumpToSignUp()

| Test Case ID | Date  | Test Scenario                                                | Test Data                            | Expected Results     | Actual Results       | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------ | -------------------- | -------------------- | ---- | ------------------------ |
| 1            | 03/07 | Click the "Sign up now!" button and see if page jumped successfully | clickOn(signInController.SignUpBtn); | Jump to sign up page | Jump to sign up page | P    | Page jumped successfully |

##### Function: jumpToSetPassword()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                 | Expected Results          | Actual Results            | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ----------------------------------------- | ------------------------- | ------------------------- | ---- | ------------------------ |
| 1            | 03/07 | Click the "Can't access your account?" button and see if page jumped successfully | clickOn(signInController.SetPasswordBtn); | Jump to set password page | Jump to set password page | P    | Page jumped successfully |

##### Function: jumpToMain()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results       | Actual Results         | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---------------------- | ---------------------- | ---- | ------------------------ |
| 1            | 03/07 | Test whether jumped to the main menu page successfully  if invoke the function | Platform.runLater(() -> {     try {         signInController.jumpToMain();     } catch (IOException e) {         e.printStackTrace();     } }); | Jump to main menu page | Jump to main menu page | P    | Page jumped successfully |

##### Function: seeDataPolicy()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results         | Actual Results           | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------ | ------------------------ | ---- | ------------------------ |
| 1            | 03/12 | Test whether jumped to the data policy page successfully  if click on the button | clickOn(signInController.DataPolicyBtn); Platform.runLater(() -> {}); | Jump to data policy page | Jump to data policy page | P    | Page jumped successfully |

##### Function: seeTerms()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results   | Actual Results      | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------ | ------------------- | ---- | ------------------------ |
| 1            | 03/12 | Test whether jumped to the terms page successfully  if click on the button | clickOn(signInController.TermsBtn); Platform.runLater(() -> {}); | Jump to terms page | Jump to  terms page | P    | Page jumped successfully |

##### Function: seeWebPage()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results     | Actual Results        | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | -------------------- | --------------------- | ---- | ------------------------ |
| 1            | 03/12 | Test whether jumped to website page successfully  if click on the button | clickOn(signInController.WebBtn); Platform.runLater(() -> {}); | Jump to website page | Jump to  website page | P    | Page jumped successfully |

------



##### SignUpController Test

##### Function: inputVerify()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                                          | Actual Results                                            | P/F  | Comments                                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | --------------------------------------------------------- | --------------------------------------------------------- | ---- | ---------------------------------------- |
| 1            | 03/07 | Set the input fields empty and check whether the error displays work successfully | TextField textField = (TextField) Main.scene.lookup("#username"); TextField emailField = (TextField) Main.scene.lookup("#email"); PasswordField passwordField = (PasswordField) Main.scene.lookup("#password"); PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm"); clickOn(textField); clickOn(emailField); clickOn(passwordField); clickOn(confirmField); clickOn(textField); | Empty notices of all the input fields popped out          | Empty notices of all the input fields popped out          | P    | The verification part works successfully |
| 2            | 03/07 | Set the input fields with incorrect format and check whether the error displays work successfully. | textField.setText("Daniel123"); clickOn(emailField); emailField.setText("123123@qq"); clickOn(passwordField); passwordField.setText("!123qweqwe"); clickOn(textField); | Error displays of all the input fields popped out         | Error displays of all the input fields popped out         | P    | The verification part works successfully |
| 4            | 03/07 | Set correct format and check whether the error displays are hidden | textField.setText("Daniel"); clickOn(emailField); emailField.setText("123123@qq.com"); clickOn(passwordField); passwordField.setText("!123qweQWE"); clickOn(confirmField); | The notifications  are hidden                             | The notifications  are hidden                             | P    | The verification part works successfully |
| 4            | 03/07 | Set confirm password different from password and check whether the error display works successfully | confirmField.setText("!123qweRTY"); clickOn(passwordField);  | "Confirm password is different with password!" popped out | "Confirm password is different with password!" popped out | P    | The verification part works successfully |
| 5            | 03/07 | Set confirm password same with password and check whether the error display is hidden | clickOn(confirmField); confirmField.setText("!123qweQWE"); clickOn(passwordField); | The notification  is hidden                               | The notification is hidden                                | P    | The verification part works successfully |

##### Function: signUp()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                                             | Actual Results                                               | P/F  | Comments                                    |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------- |
| 1            | 03/08 | Test the situations of   no network connections, check whether the error message displayed | TextField textField = (TextField) Main.scene.lookup("#username");             TextField emailField = (TextField) Main.scene.lookup("#email");            PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");       PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm");  clickOn(textField); textField.setText("Dave"); clickOn(emailField);                      emailField.setText("456567cc@qq.com"); clickOn(passwordField); passwordField.setText("!123qweQWE"); clickOn(confirmField); confirmField.setText("!123qweQWE"); clickOn(signUpController.SignUpBtn); Platform.runLater(() -> {     signUpController.connectWarning.setVisible(true); }); | "Can't access the database, please check your network" popped out | "Can't access the database, please check your network" popped out | P    | Correct notification displayed successfully |
| 2            | 03/08 | Set username and email that have exist and check whether the error displays work successfully when clicking sign up button | clickOn(textField);           textField.setText("Danny");  clickOn(signUpController.SignUpBtn); Platform.runLater(() -> { });           clickOn(textField);              textField.setText("Dave");           clickOn(emailField);                     emailField.setText("2692521364@qq.com");  clickOn(signUpController.SignUpBtn);  Platform.runLater(() -> {  }); | "The username has exist!" and "The email address has exist" popped out | "The username has exist!" and "The email address has exist" popped out | P    | Correct notification displayed successfully |
| 3            | 03/08 | Test whether user can sign up successfully with correct user information | clickOn(emailField);                     emailField.setText("456567cc@qq.com"); clickOn(signUpController.SignUpBtn); | An alert of sign up successfully popped out and then turn to the sign in page | An alert of sign up successfully popped out and then turn to the sign in page | P    | Sign up and page jump is succeed            |

##### Function: jumpToSignIn()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results     | Actual Results       | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | -------------------- | -------------------- | ---- | ------------------------ |
| 1            | 03/08 | Test whether jumped to the sign in page successfully  if invoke the method | Platform.runLater(() -> {     try {         signUpController.jumpToSignIn();     } catch (IOException e) {         e.printStackTrace();     } }); | Jump to sign in page | Jump to sign in page | P    | Page jumped successfully |

------



##### SetPasswordController Test

##### Function: inputVerify()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                                          | Actual Results                                            | P/F  | Comments                                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | --------------------------------------------------------- | --------------------------------------------------------- | ---- | ---------------------------------------- |
| 1            | 03/08 | Set the input fields empty and check whether the error displays work successfully | TextField emailField = (TextField) Main.scene.lookup("#email"); PasswordField passwordField = (PasswordField) Main.scene.lookup("#password"); PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm"); clickOn(emailField); clickOn(passwordField); clickOn(confirmField); clickOn(emailField); | Empty notices of all the input fields popped out.         | Empty notices of all the input fields popped out          | P    | The verification part works successfully |
| 2            | 03/08 | Set the input fields with incorrect format and check whether the error displays work successfully | emailField.setText("123456@cloud"); clickOn(passwordField); passwordField.setText("!123qweq"); clickOn(emailField); | Error displays of all the input fields popped out         | Error displays of all the input fields popped out         | P    | The verification part works successfully |
| 3            | 03/08 | Set correct format and check whether the error displays are hidden | emailField.setText("123456@cloud.com");                      clickOn(passwordField); passwordField.setText("!123qweQWE"); clickOn(confirmField); | The notifications  are hidden                             | The notifications  are hidden                             | P    | The verification part works successfully |
| 4            | 03/08 | Set confirm password different from password and check whether the error display works successfully | confirmField.setText("!223qweQWE"); clickOn(passwordField);  | "Confirm password is different with password!" popped out | "Confirm password is different with password!" popped out | P    | The verification part works successfully |
| 5            | 03/08 | Set confirm password same with password and check whether the error display is hidden | clickOn(confirmField); confirmField.setText("!123qweQWE"); clickOn(passwordField); | The notification  is hidden                               | The notification is hidden                                | P    | The verification part works successfully |

##### Function: sendCode()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                                             | Actual Results                                               | P/F  | Comments                                                     |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------------------------ |
| 1            | 03/08 | Set wrong email format, check whether the send code work if clicking the button | TextField emailField = (TextField) Main.scene.lookup("#email");                                TextField codeField = (TextField) Main.scene.lookup("#veriCode");                  clickOn(emailField);               emailField.setText("333@cloud"); clickOn(setPasswordController.sendBtn); | The sendCode  function returns                               | The sendCode  function returns                               | P    | Correct prevention for working on wrong format email address |
| 2            | 03/08 | Test the situations of   no network connections, check whether the error message displayed | Platform.runLater(() -> {     setPasswordController.connectWarning.setVisible(true); }); | "Can't access the database, please check your network" popped out | "Can't access the database, please check your network" popped out | P    | Correct notification displayed successfully                  |
| 3            | 03/08 | Set a not-registered email, check whether the error message displays if clicking the button | clickOn(emailField);                                    emailField.setText("26925213@qq.com"); clickOn(codeField); clickOn(setPasswordController.sendBtn); Platform.runLater(() -> {}); | "The email address has not been registered!" popped out      | "The email address has not been registered!" popped out      | P    | Correct notification displayed successfully                  |
| 4            | 03/08 | Set a correct email, check whether the code sends successfully | clickOn(emailField);                               emailField.setText("2692521364@qq.com"); clickOn(codeField); clickOn(setPasswordController.sendBtn); Platform.runLater(() -> {}); | The email is sent and the button began to count down         | The email is sent and the button began to count down         | P    | Send code successfully                                       |
| 5            | 03/08 | Test whether the email can be resend if clicking the button again in 60s | clickOn(emailField); clickOn(setPasswordController.sendBtn); | No any response to user                                      | No any response to user                                      | P    | Prevent resend in 60s successfully                           |

##### Function: createPassword()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results                                             | Actual Results                                               | P/F  | Comments                                                     |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------------------------ |
| 1            | 03/08 | Test whether the create password function works if clicking the button | TextField emailField = (TextField) Main.scene.lookup("#email");                                  TextField codeField = (TextField) Main.scene.lookup("#veriCode");                   PasswordField passwordField = (PasswordField) Main.scene.lookup("#password");                 PasswordField confirmField = (PasswordField) Main.scene.lookup("#confirm");                clickOn(emailField);                                        emailField.setText("2692521364@qq.com");        clickOn(codeField);                         codeField.setText("123ASD");            clickOn(passwordField); passwordField.setText("!123qweQWE"); clickOn(confirmField); confirmField.setText("!123qweQWE"); clickOn(setPasswordController.SetPasswordBtn); | No any response to user                                      | No any response to user                                      | P    | Prevent reset password with no verification code successfully |
| 2            | 03/08 | Set wrong input field and check whether the function works if clicking the button | clickOn(setPasswordController.sendBtn); Platform.runLater(() -> {});                        clickOn(codeField); codeField.setText(setPasswordController.code.toString()); clickOn(passwordField); passwordField.setText("!123qweQWE"); clickOn(confirmField); confirmField.setText("!123qweQWEr"); clickOn(setPasswordController.SetPasswordBtn); Platform.runLater(() -> {}); | Can't do reset password operations                           | Can't do reset password operations                           | P    | Correct notification displayed successfully                  |
| 3            | 03/08 | Set another email after sending a  mail, check whether the function works if clicking the button | clickOn(confirmField); confirmField.setText("!123qweQWE");  clickOn(emailField); emailField.setText("123@cloud.com"); clickOn(setPasswordController.SetPasswordBtn); Platform.runLater(() -> {}); | "Not allowed to input another email address after verification!" popped out | "Not allowed to input another email address after verification!" popped out | P    | Correct notification displayed successfully                  |
| 4            | 03/08 | Set code field empty or set a wrong code, check whether the error displays work successfully if clicking the button | clickOn(emailField);                                          emailField.setText("2692521364@qq.com"); clickOn(codeField);                                           codeField.setText("");                                 clickOn(passwordField); clickOn(setPasswordController.SetPasswordBtn); Platform.runLater(() -> {});                                clickOn(codeField);                          codeField.setText("123ASD");                        clickOn(passwordField); clickOn(setPasswordController.SetPasswordBtn); Platform.runLater(() -> {}); | "The verification code is incorrect!" popped out             | "The verification code is incorrect!" popped out             | P    | Correct notification displayed successfully                  |
| 5            | 03/08 | Set correct code, check whether the error displays is hidden | clickOn(codeField); codeField.setText(setPasswordController.code.toString()); clickOn(passwordField); | The notification  is hidden                                  | The notification  is hidden                                  | P    | Notification hidden successfully                             |
| 6            | 03/08 | Test whether user can reset password successfully with correct user information | clickOn(setPasswordController.SetPasswordBtn); Platform.runLater(() -> {}); | An alert of sign up successfully popped out and then turn to the sign in page | An alert of sign up successfully popped out and then turn to the sign in page | P    | Reset password and page jump is succeed                      |

##### Function: jumpToSignIn()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results     | Actual Results       | P/F  | Comments                 |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | -------------------- | -------------------- | ---- | ------------------------ |
| 1            | 03/08 | Test whether jumped to the sign in page successfully  if invoke the method | Platform.runLater(() -> {     try {         signUpController.jumpToSignIn();     } catch (IOException e) {         e.printStackTrace();     } }); | Jump to sign in page | Jump to sign in page | P    | Page jumped successfully |

------



##### UserInformController Test

##### Function: addTable()

| Test Case ID | Date  | Test Scenario                                                | Test Data                         | Expected Results                                             | Actual Results                                               | P/F  | Comments                                               |
| ------------ | ----- | ------------------------------------------------------------ | --------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ------------------------------------------------------ |
| 1            | 03/09 | Test whether the user inform page displayed and deletion operates successfully if invoke the method. | clickOn(Main.scene.lookup("#1")); | Displaying the user inform page and delete the table in database successfully | Displaying the user inform page and delete the table in database successfully | P    | Page displayed successfully and deletion is successful |

------



##### InputField Test

##### Function: getRegex()

| Test Case ID | Date  | Test Scenario                              | Test Data                                                    | Expected Results | Actual Results   | P/F  | Comments                    |
| ------------ | ----- | ------------------------------------------ | ------------------------------------------------------------ | ---------------- | ---------------- | ---- | --------------------------- |
| 1            | 03/09 | Test if user can get regex field correctly | InputField inputField = new InputField("^[a-zA-Z]{2,15}$", "^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$", "Username or Email address", true, false);      assertEquals("^[a-zA-Z]{2,15}$", inputField.getRegex()); | ^[a-zA-Z]{2,15}$ | ^[a-zA-Z]{2,15}$ | P    | Get the correct regex field |

##### Function: getRegex2()

| Test Case ID | Date  | Test Scenario                               | Test Data                                                    | Expected Results                                             | Actual Results                                               | P/F  | Comments                     |
| ------------ | ----- | ------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---------------------------- |
| 1            | 03/09 | Test if user can get regex2 field correctly | assertEquals("^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$", inputField.getRegex2()); | ^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$ | ^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$ | P    | Get the correct regex2 field |

##### Function: getInputType()

| Test Case ID | Date  | Test Scenario                                  | Test Data                                                    | Expected Results          | Actual Results            | P/F  | Comments                        |
| ------------ | ----- | ---------------------------------------------- | ------------------------------------------------------------ | ------------------------- | ------------------------- | ---- | ------------------------------- |
| 1            | 03/09 | Test if user can get inputType field correctly | assertEquals("Username or Email address", inputField.getInputType() | Username or Email address | Username or Email address | P    | Get the correct inputType field |

##### Function: getIsInvalid()

| Test Case ID | Date  | Test Scenario                                  | Test Data                                      | Expected Results | Actual Results | P/F  | Comments                        |
| ------------ | ----- | ---------------------------------------------- | ---------------------------------------------- | ---------------- | -------------- | ---- | ------------------------------- |
| 1            | 03/09 | Test if user can get isInvalid field correctly | assertEquals(true, inputField.getIsInvalid()); | true             | true           | P    | Get the correct isInvalid field |

##### Function: getIsAdded()

| Test Case ID | Date  | Test Scenario                                | Test Data                                     | Expected Results | Actual Results | P/F  | Comments                      |
| ------------ | ----- | -------------------------------------------- | --------------------------------------------- | ---------------- | -------------- | ---- | ----------------------------- |
| 1            | 03/09 | Test if user can get isAdded field correctly | assertEquals(false, inputField.getIsAdded()); | false            | false          | P    | Get the correct isAdded field |

##### Function: setIsAdded()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results | Actual Results | P/F  | Comments                        |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---------------- | -------------- | ---- | ------------------------------- |
| 1            | 03/09 | Test if user can set isAdded field correctly by getting the settled data | inputField.setIsAdded(true); assertEquals(true, inputField.getIsAdded()); | true             | true           | P    | Set the isAdded field correctly |

##### Function: setIsInvalid()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                                    | Expected Results | Actual Results | P/F  | Comments                          |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---------------- | -------------- | ---- | --------------------------------- |
| 1            | 03/09 | Test if user can set isInvalid field correctly by getting the settled data | inputField.setIsInvalid(false); assertEquals(false, inputField.getIsInvalid()); | false            | false          | P    | Set the isInvalid field correctly |

------



##### UserInformController Test

##### Function: addComparison()

| Test Case ID | Date  | Test Scenario                                                | Test Data                             | Expected Results     | Actual Results       | P/F  | Comments                     |
| ------------ | ----- | ------------------------------------------------------------ | ------------------------------------- | -------------------- | -------------------- | ---- | ---------------------------- |
| 1            | 03/16 | Click the ADD button to test whether user can add comparison | clickOn(Main.scene.lookup("#AddBtn")) | New comparison added | New comparison added | P    | Add comparison successfully. |

##### Function: showSymbols()

| Test Case ID | Date  | Test Scenario                                                | Test Data                                      | Expected Results | Actual Results   | P/F  | Comments                       |
| ------------ | ----- | ------------------------------------------------------------ | ---------------------------------------------- | ---------------- | ---------------- | ---- | ------------------------------ |
| 1            | 03/16 | Click the CheckBox to control whether show the line chart symbols or not | clickOn(Main.scene.lookup("#ShowSymbolCheck")) | Hide the symbols | Hide the symbols | P    | Hide the symbols successfully. |

