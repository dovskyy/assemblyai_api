# Audio-to-Text REST client 

## Project Description

This project is a simple Java application that takes an audio file and transcribes it into text using the AssemblyAI service for transcription. The application consists of two main classes: `Main` and `Transcript`.

### Project Features

- Sending an audio file to the AssemblyAI service to obtain a transcription.
- Monitoring the transcription's status until it is completed.
- Displaying the transcription result in the console.
- Using AssemblyAI REST API

## Requirements

To run this project, you need to have the following tools and libraries installed:

- Java 11
- Gson library
- HttpClient library (available in Java 11)
- AssemblyAI API Authorization Key

## Configuration

Before running the project, make sure you have an AssemblyAI API Authorization Key. You can obtain it by signing up on the [AssemblyAI website](https://www.assemblyai.com/).

After obtaining the API Authorization Key, paste it into the code where it says:

```java
.header("Authorization", "YOUR_API_AUTHORIZATION_KEY")
```

## Running the Project

1. Clone the repository to your local machine.

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

2. Open the project in IntelliJ IDEA or any other code editor.

3. In the `Main` class, update the audio file URL to your own if it's different from the one used in the example.

4. In the `Main` class, enter your AssemblyAI API Authorization Key.

5. Run the application.

## Usage

After running the project, the application will send the audio file to the AssemblyAI service and monitor the transcription's status. The transcription result will be displayed in the console once the process is completed.ct was created by [Your Name]. You can contact me at [Your Email Address].
