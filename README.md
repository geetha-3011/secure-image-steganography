# 🔐 Secure Image Steganography System
### Secure Information Concealment and Retrieval System

A Java-based desktop application that securely embeds and extracts confidential text within digital images using **AES encryption** and **LSB image steganography**.  
The project focuses on data confidentiality, secure communication, and practical implementation of information security concepts.

---

## 📌 Project Overview

In today’s digital environment, protecting sensitive information during storage and transmission is critical.  
This project demonstrates how confidential data can be securely hidden inside images while remaining visually unchanged.

The system combines:
- **Cryptography** (to encrypt data)
- **Steganography** (to conceal encrypted data inside images)

A clean and user-friendly **Java Swing interface** allows users to embed and retrieve data with ease.

---

## 🎯 Key Features

- Secure text encryption using **AES (Advanced Encryption Standard)**
- Image-based data hiding using **Least Significant Bit (LSB)** technique
- Password-protected data extraction
- Image capacity validation before embedding
- User-friendly desktop interface
- Robust input validation and error handling

---

## 🛠️ Technologies Used

- **Programming Language:** Java  
- **UI Framework:** Java Swing  
- **Encryption Algorithm:** AES  
- **Image Processing:** BufferedImage, ImageIO  
- **Development Tools:** VS Code / Eclipse / IntelliJ IDEA  

---

## 📂 Project Structure

Secure-Image-Steganography/
│
├── core/
│   └── Steganography.java
│
├── ui/
│   └── Main.java
│
├── util/
│   ├── CryptoUtils.java
│   └── ValidationUtils.java
│
├── screenshots/
│   └── Application output images
│
└── README.md

---

## ⚙️ How the System Works

### 🔹 Data Embedding Process
1. User selects a cover image
2. Confidential text is entered
3. An encryption key is provided
4. The text is encrypted using AES
5. Encrypted data is embedded into the image using LSB technique
6. A secure stego-image is generated and saved

### 🔹 Data Extraction Process
1. User selects the stego-image
2. The correct encryption key is entered
3. Hidden encrypted data is extracted
4. AES decryption retrieves the original message

---

## 🧪 Validation and Security Measures

- Minimum password length enforcement
- Image capacity validation before embedding
- Safe failure for incorrect keys
- Prevention of empty or invalid inputs

---

## 📸 Screenshots

All screenshots related to:
- Home screen
- Data embedding process
- Successful encryption
- Data extraction
- Error handling scenarios  

are available in the **screenshots/** folder.

---

## ▶️ How to Run the Project

# Clone the repository
git clone https://github.com/geetha-3011/secure-image-steganography.git

# Navigate to the project directory
cd secure-image-steganography

# Compile the project
javac ui/*.java core/*.java util/*.java

# Run the application
java ui.Main

---

## 📈 Applications and Use Cases

- Secure data communication using images
- Protection of sensitive information
- Academic learning of cryptography and steganography
- Demonstration of secure desktop application development

---

## 🚀 Future Enhancements

- Support for audio and video steganography
- Stronger key derivation techniques (PBKDF2)
- File-based data embedding
- Executable packaging for cross-platform use

---

## 👩‍💻 Author

**GeethaLakshmi. T**  
*Final Year B.Tech (Information Technology)*  

📧 **Email:** geethalakshmi0399@gmail.com  
🔗 **LinkedIn:** [linkedin.com/in/geethalakshmi3011](https://www.linkedin.com/in/geethalakshmi3011)  
💼 **GitHub:** [github.com/geetha-3011](https://github.com/geetha-3011)

---

## 📄 License

This project is developed for educational and learning purposes.
Free to use and modify with proper attribution.

---