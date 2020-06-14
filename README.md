# LocationBasedService
Mobile computing IOT project Using Coap Protocol, Implemented in JAVA


The principle of this project is based on client- server and Request-Response model using Rest service ,Http protocol , CoAP(IOT) Protocol and IOT Gateway.


1.	 Client :- web browser is acting as a client which is responsible for sending Http Request and Receiving Http Response in XML Data Format. REST(Representational State Transfer) web service is created in order to handle  GET and POST Http Request Response.

2.	Controller:-Controller is act as IOT Gateway. It is responsible for  Protocol translation from Http to coAP  and is responsible for handling Request- Response from web browser to sensor actuator along with the required xml &JSON Format conversion. Controller one end is act as a web server and other end act as coap client therefore, called as Gateway.

3.	IOT Emulated Environment:- This environment is created while defining sensors and actuator as a java application(Fig: 1). All sensors and actuators are acting as separate resource running on different coAp ports.
