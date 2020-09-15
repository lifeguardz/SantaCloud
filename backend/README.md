# api-santacloud
## Description
Backend server for the SantaCloud application.

## Resources
- Java 14
- Spring Boot
- Maven
- Okta 
- MySQL

## Getting Started
To install this example application, run the following commands:

```bash
git clone https://github.com/lifeguardz/SantaCloud.git
```

This will get a copy of the project locally.

## How Run Firt Time
1. Import project in IDE as Maven Project.
2. Change the MySql configuration to your database server. This can be changed in an `application.yml` file.
A template for this file is stored in the project as `application.yml_samples`.
Create an empty Database called `santacloud`.
3. [Create an OAuth 2.0 app in Okta](https://developer.okta.com/docs/guides/implement-oauth-for-okta/create-oauth-app/)
Set the access data in the `application.yml`. [Add Role-Based Access Control](https://developer.okta.com/blog/2017/10/13/okta-groups-spring-security#authorization-server) 
and create the user groups `ADMIN`, `OFFICE`, `SANTA` and `OS`. Now users can be created and assigned to the different groups. 
4. Run project as Java Application.
5. Server is started with the default port `8080`.

## Endpoint Description
### Swagger Docs
    [SERVER-IP:PORT]/v2/api-docs
### Swagger UI
    [SERVER-IP:PORT]/docs

## License
GNU General Public License v3.0
