java version "22.0.2" 2024-07-16
Apache Maven 3.9.8
Apache Commons CLI
<dependency>
            <groupId>commons-cli</groupId>
            <artifactId>commons-cli</artifactId>
            <version>1.8.0</version>
</dependency>

Для запуска:
    1)mvn package из корневой директории проекта. В папке target будет создан jar файл
    2)java -jar fullpath\target\TestTask-1.0-SNAPSHOT-jar-with-dependencies.jar -f -p nice- -o /new/nee in2.txt java
    папка из которой будет вызван jar будет считаться рабочей директорией
