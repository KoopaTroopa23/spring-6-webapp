package guru.springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Creating and saving an author, Eric Evans
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        // Creating and saving a book, Domain Driven Design
        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        // Creating and saving a publisher
        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setZipCode("12345");

        Publisher savedPublisher = publisherRepository.save(publisher);

        // Associating the saved publisher with the book
        dddSaved.setPublisher(savedPublisher);
        bookRepository.save(dddSaved);

        // Creating and saving another author, Rod Johnson
        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        // Creating and saving another book, J2EE Development without EJB
        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("987654");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        // Associating the saved publisher with the second book
        noEJBSaved.setPublisher(savedPublisher);
        bookRepository.save(noEJBSaved);

        // Printing the count of publishers to the console
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
