# ⚙️ KGenP - Nginx Configuration Generator for Websites

**KGenP** (Kamikotto Generator for Projects) is a console Kotlin application designed to automatically generate website structure and Nginx configuration files based on user input. The utility is simple to use, helps developers quickly deploy static websites, and includes convenient project file organization. The program prompts the user for a domain name and the path to an HTML file, verifies the existence of `index.html`, then copies it to a new directory, creates an Nginx configuration, writes it to a file, and outputs the path to the created structure.

## 🧩 Features

- Interactive generation mode
- Support for custom domains
- Automatic file and directory creation
- Generation of correct Nginx configuration
- Removal of the original HTML file after copying
- Variable escaping for proper Kotlin output

## 📁 Output Structure

After generation, the following structure is created:

```
src/main/resources/site-[domain]/
├── html/
│   └── index.html
└── nginx/
    └── [domain]
```

Where `html/index.html` is the website file, and `nginx/[domain]` is the configuration file for the Nginx server.

## 🔧 Configuration File Contents

The configuration is generated dynamically and looks like this:

```nginx
server {
    listen 80;
    server_name .example.com;

    root /var/www/example.com;
    index index.html;

    location / {
        try_files $uri $uri/ =404;
    }
}
```

## 🚀 Quick Start

1. Clone the repository:

```bash
git clone https://github.com/your-username/kgenp.git
cd kgenp
```

2. Run the project from your IDE (e.g., IntelliJ IDEA) or via Gradle:

```bash
./gradlew run
```

3. Follow the console instructions:
   - Enter your website domain
   - Specify the path to `index.html`
   - Done — the project structure and configuration are saved
