# Tic-Stat-Toe

Tic-Stat-Toe is a small web application where we gather and analyse the data we get when playing a classic game of tic-tac-toe (Noughts and Crosses).

Simply play a game, and then explore the results. See how long games take, see which player normally comes out on top, which square is best to take first. etc. 

## Stack

The front end is [React](https://reactjs.org/) with [TypeScript](https://www.typescriptlang.org/) build from [`create-react-app`](https://reactjs.org/docs/create-a-new-react-app.html). [Yarn](https://yarnpkg.com/) is used for package management and it is also uses Microsofts design system [FluentUI](https://developer.microsoft.com/en-us/fluentui#/).

The backend is [Java 14](https://openjdk.java.net/projects/jdk/14/) with [Maven](https://maven.apache.org/) and [Spring boot](https://spring.io/projects/spring-boot) framework. For our database we use [Postgresql](https://www.postgresql.org/). 

## Set up

### Front end

To start up the front end side of the application we need to have [yarn](https://yarnpkg.com/) installed. 

Once this is done we need to be in the `client` directory. Here we then install our packages with

```sh
yarn install
```

 This will create our `node_modules` directly and then we're good to go. 

Our final step is to run

```sh
yarn start
```

The application should then open up in your browser at `http://localhost:3000/`

### Back end

To get the backend working we need to have [Java 14](https://openjdk.java.net/projects/jdk/14/) and [Maven](https://maven.apache.org/download.cgi) downloaded and installed. 

Next step is to set up your local [Postgresql](https://www.postgresql.org/) database. For connection information see `application.properties` file. 

Once all this is done we should be able to run the server with the following command:

```sh
mvn spring-boot:run
```

That's it! we should have a working local version of the application communicating with a local database. If you notice any problems or think this explanation need improving then just create an issue in the [Github](https://github.com/jacklovett/Tic-Stat-Toe) project
