JAVA

    Object model

    interface EmailService {
        sendEmail(Email email)
        emailOrder
        emialInvoice
        forgotPassword
        registrationConfirmation
    }

    class SmtpEmailService implements EmailService {}
    class AWSSNEmailService implements EmailService {}
    class SendGridEmailService implements EmailService {}

    class TextService {
        sendText(Text text)
    }

    class PrintService {
        postOrder(Order order)
    }

    class OrderService { --- may fail, life cycle should be controlled by someone
        synchonized storeOrdertoDB(Order order)
    }

    WebService shall be Object

1 Order

    WS      --->    Order 
          storeOrdertoDB [Communication] / tightly coupled mechnism

3 Orders 

1ms      WS      --->    Order 1
          orderService.storeOrdertoDB [Communication] / tightly coupled mechnism [200 ms] [wait]/blocking
          // refactor
          emailService.sendEmail (order)
          // text
          TextService.sendText(order)

          postalSErvice.postOrder(order)

          mobileNotification.sendNotificatin(order)
          still pending 
          exit after 200 ms
5ms      WS      --->    Order 2
          excute orderService.storeOrdertoDB [Communication] / tightly coupled mechnism [200 ms] [wait]/blocking
            exit after 100 ms 



    class WebService {
        @Route(//)
        @Post(/submit-order)
        actionMethod(Order order) {
            // Object COMMUNICATION
            orderService.storeOrdertoDB(order)
        }
    }


    class EmailServiceThread {
        Queue<Order> orders; // shared memory

        public void enque(Order order) {
            ordres.add(order)
        }

        public void run() throws Exception {
            while (true) {
                if (orders.length == 0) continue;

                Order order = orders.deque();
                sendEmail(order)
            }
        }
    }


    class OrderService {
        @Post
        @Route
        submitOrder(Order order) {
            emailServiceThred.enque(queue)
        }
    }

    class InvoiceService {
        emailServiceThred.enque(queue)
    }



    emailActor: EmailActor 
        

    textActor: TextActor

    OrderServiceActor

    MobileNotificationActor


    WebServiceActor/Non-Actor
            // emailActor is not an instance of EmailService
            Actor emailActor = getActorRef("emailActor")
            emailActor.tell (order)

            Actor textActor = getActorRef("textActor")

            textActor.tell (order)









    interface EmailService {
        sendEmail(Email email)
        emailOrder
        emialInvoice
        forgotPassword
        registrationConfirmation
    }

    to 
    interface EmailService {
        send(Object obj)
    }


    WebService
        emailService: EmailService = new SmtpEmailService() // Java
        route 
            forgotPassword
                emailActor.send(resetPassword)

            submitOrder
                emailActor.send(order)


Actor send message to another actor 
           generic/anything

Pattern 1 - Tell Fire/Forgot, no answer expected

Bus is gone - message

Actor 1    ------->    Actor 2

getActorRef('actor2').tell (message)

Actor 1    <-------    Actor 2

                        getActorRef('actor1').tell (message)
----

Pattern 2 - Demand Response / Ask pattern

Is Bus gone? - Ask, need answer
    Answer needed
        Wait for answer
            Sync - blocing
            Async - non blocking


Actor 1                                          ----------> Actor 2

getActorRef('actor2').ask ('Is Bus Gone?')

actor 1          <------------------------------------------ Actor 2
                                      Need to give answer
                                      getActorRef('actor1').reply ('Yes')
                                        NOT TELL...

forward, discussed later


                Root
          system             users
                                 helloworld
                                     hello1 [HelloWorldActor]
                                     
                                     
                        /users/helloworld/hello1