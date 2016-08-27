## PARC-WebApp

This a web application designed for a [Privacy-preserving Data Cleaning framework](http://macsphere.mcmaster.ca/bitstream/11375/18075/2/gairola_dhruv_201507_msc_computer_science.pdf). It can help clean the dirty dataset in a privacy-preserving manner. [Grails framework](https://grails.org/) is used as the web framework. This project uses the [PARC-Core](https://github.com/dejunhuang/PARC-Core) project as a wrapper Java library for the [privacyCleaning](https://github.com/dhruvgairola/privacyCleaning) project.

### Working Environment

Grails 2.4.4, Java 8 (1.8.0_25) 64-bit, Maven 3.0.3, MySQL 5.6.22

### Installation 

The project uses Grails as the web framework and use a wrapper for running grails commands so that users are not required to install grails locally.

Install the dependencies into the local repository: [PARC-Core](https://github.com/dejunhuang/PARC-Core), [privacyCleaning](https://github.com/dhruvgairola/privacyCleaning)

To download the project:

```
git clone https://github.com/dejunhuang/PARC-WebApp.git
```

The web application uses MySQL as the database. For development, it require the database named **data_privacy_test**, the default connecting user profile is *(username: data_pri_test, password: test)*. The database connection settings can be customized in the **/grails-app/conf/DataSource.groovy** file. Please edit this file for your own environment settings.

After configuring the database connection settings correctly, run the application in the terminal:

```
cd PARC-WebApp
./grailsw compile
./grailsw run-app
```

These commands are for development purpose. Please see the tutorials [here](http://grails.github.io/grails-doc/2.4.4/guide/deployment.html) for deployment on the Tomcat server.
