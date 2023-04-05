# socketio-server-demo
[Socket.io](https://socket.io/) Server with Spring Boot

![](https://img.shields.io/badge/Spring-informational?style=for-the-badge&logo=Spring&logoColor=green&color=white)
![](https://img.shields.io/badge/SpringBoot-informational?style=for-the-badge&logo=SpringBoot&logoColor=green&color=white)
![](https://img.shields.io/badge/Socket.io-informational?style=for-the-badge&logo=Socket.io&logoColor=010101&color=grey)
![](https://img.shields.io/badge/IntellijIDEA-informational?style=for-the-badge&logo=IntellijIDEA&logoColor=white&color=black)

### Techs & Specs
    - Java 17 & SpringBoot 3
    - Socket.io
    - H2 Database
    - Docker

### HOW TO

Connect Socket Server via Postman
   - Select "Socket.IO"
   - ws://<APP_LOCAL_IP>?room=<ROOM_NAME>&username=<USER_NAME>
   - Choose client version "V2" from "Settings"
   - Connect
   - Write your JSON request inside message with required parameters
   - Event Name --> send_message (default: message)
   - Send

<img src="./img/postman-ws-connect.png" alt="postman-request">