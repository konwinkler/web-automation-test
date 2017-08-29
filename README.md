# Name Game Test Automation

### Tools
- [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Intellij Community Edition](https://www.jetbrains.com/idea/download/#section=mac)
- [Google Chrome](https://www.google.com/chrome/browser/desktop/index.html)

### Importing
- Clone/Download this project
- Intellij
  - File > Open
  - Navigate to this project
  - Double click on the `build.gradle`
  - Click on `Open as project`
  - Click on `Delete existing project and import` (if prompted)
  
### Running Tests
- Intellij all tests
  - `test > resources`
  - Right click on `TestNG.xml`
  - Click on `Run TestNG.xml`
- Intellij single test
  - `test > java > com.willowtreeapps`
  - Right click on any class
  - Click on `Run <className>`
- bash: `./gradlew test`
- windows cmd: `gradle test`
