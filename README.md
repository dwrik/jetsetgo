# Flight Booking System API

## Overview

Flight Booking System is a Spring based microservices project using technologies like Spring Cloud (Eureka, Config, Gateway, Stream), Spring Data JPA, JWT etc. among other technologies. It simulates the backend of a Flight Booking System and was built with the primary focus of implementing a full fledged microservices architecture.

The API allows users to:

- register and login
- fetch flight details based on location and date
- book seats, fetch bookings and delete bookings
- checkin

## Documentation

Postman generated API Documentation can be found [here](https://documenter.getpostman.com/view/25773831/2s93CSoWPA).

## Services

The project consists of 7 different services that interact with each other using internal REST calls or Messaging. Each service handles the responsibilities of it's own domain. Here is the detailed breakdown for each service:

| Service | Responsibility                                                   |
|:------- |:---------------------------------------------------------------- |
| Eureka  | Service registration and discovery                               |
| Gateway | Routing and Authentication                                       |
| Config  | Hosts configuration properties for each of the services          |
| Auth    | User registration and login                                      |
| Flight  | Fetch flight details, reserve seats and vacate seats on a flight |
| Booking | Fetch, create and delete bookings                                |
| Checkin | Checkin functionality                                            |

## Messaging

The application uses Spring Cloud Stream along with RabbitMQ for messaging. The following are the message flows involved in the application:

- Checkin subscribes to Booking for new bookings that are yet to checkin
- Booking subscribes to Checkin for updates to checkin status & seat number
- Flight and Checkin subscribes to Booking for cancellation updates

## Todo

- Dockerizing the application, including the database and message binder.
- Deploying the application to the web.

## Related Links

- Flight Booking System Next.js based frontend can be found [here](https://github.com/dwrik/flight-booking-system-frontend).
- Flight Booking System Config repository can be found [here](https://github.com/dwrik/flight-booking-system-config).
