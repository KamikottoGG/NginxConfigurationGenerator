package com.kamikotto.core

import java.io.File
import java.io.FileWriter

class SiteGenerator {
    fun startWork(){

        greeting()

        generate()

    }

    private fun generate(){

        println("[*] Для создания конфигурации, нужно ввести некоторые данные")

        print("[?] Введите домен сайта: ")
        val domain = readln()

        print("[?] Введите путь к файлу index.html: ")

        val indexFilePath = readln()

        val indexFile = File(indexFilePath)

        if (!indexFile.exists()){

            println("[!] Работа не возможна. Файл не найден, или не существует")

            return
        }

        val nginxConfigurationText = """
            server {
                listen 80;
                server_name .$domain;
        
                root /var/www/$domain; 
                index index.html;
        
                location / {
                    try_files ${'$'}uri ${'$'}uri/ =404;
                }
            }
        """.trimIndent()

        val siteDoneFolder = File("src/main/resources/site-$domain")

        siteDoneFolder.mkdir()

        val htmlFolder = File(siteDoneFolder.path + "/html")

        htmlFolder.mkdir()

        indexFile.copyTo(File(htmlFolder.path + "/index.html"))
        indexFile.delete()
        val nginxConfDir = File(siteDoneFolder.path + "/nginx")

        nginxConfDir.mkdir()

        val nginxConfFile = File(nginxConfDir.path + "/$domain")

        val writer = FileWriter(nginxConfFile)

        writer.write(nginxConfigurationText)

        writer.close()

        println("[+] Файлы были успешно сохранены!")
        println("[*] Путь: ${siteDoneFolder.absolutePath}")
    }

    private fun greeting(){

        println("[*] Добро пожаловать в генератор nginx-конфигурации сайтов! [KGenP-1]")

    }



}