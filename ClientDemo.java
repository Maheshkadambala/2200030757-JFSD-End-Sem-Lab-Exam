package com.klu.HibernateCRUD;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Client client1 = new Client();
            client1.setName("Alice");
            client1.setGender("Female");
            client1.setAge(28);
            client1.setLocation("New York");
            client1.setEmail("alice@example.com");
            client1.setMobile("1234567890");

            Client client2 = new Client();
            client2.setName("Bob");
            client2.setGender("Male");
            client2.setAge(32);
            client2.setLocation("Los Angeles");
            client2.setEmail("bob@example.com");
            client2.setMobile("0987654321");

            session.save(client1);
            session.save(client2);

            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Query<Client> query = session.createQuery("FROM Client", Client.class);
            List<Client> clients = query.list();

            for (Client client : clients) {
                System.out.println("ID: " + client.getId());
                System.out.println("Name: " + client.getName());
                System.out.println("Gender: " + client.getGender());
                System.out.println("Age: " + client.getAge());
                System.out.println("Location: " + client.getLocation());
                System.out.println("Email: " + client.getEmail());
                System.out.println("Mobile: " + client.getMobile());
            }
        }

        factory.close();
    }
}
