A simple Java server application that supports "telnet"-like connections. A "telnet" like application can be used
for testing the server.  The server supports multiple concurrent connections and it responds to very basic commands
like "ls", "cd", "mkdir", "pwd". It also is portable across platforms (build time and run time).

This does not use the classes that invoke native commands (e.g. Runtime or ProcessBuilder).  This project can be
built with Maven 2.

How to build

Run the following maven command from the project's root directory, for example
> cd /TelnetServer
> mvn clean install

How to run the telnet server

Run the following java command from the project's root directory, for example
>  java -jar target/TelnetServer-1.0.jar

Notes:

This is a basic telnet server that responds to the following client commands:
 - pwd
 - ls
 - cd <destination>
 - mkdir <sub-dir name>

This is not a robust application, i.e. it does not perform all sort of validations for a given command nor does
it accepts any further command line options for the supported command.  This application is a basic telnet server
app.

It does not change to the home directory when there is no argument given to the 'cd' command, as compare to what
linux like servers shells do.