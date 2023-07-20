import org.flywaydb.core.Flyway;
import service.ClientCrudService;
import service.HibernateUtil;
import service.PlanetCrudService;
import service.TicketCrudService;

import java.util.ResourceBundle;

public class DemoHibernate {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("hibernate");
    private static final String JDBC_URL = "hibernate.connection.url";
    private static final ClientCrudService clientCrudService = new ClientCrudService();
    private static final PlanetCrudService planetCrudService = new PlanetCrudService();
    private static final TicketCrudService ticketCrudService = new TicketCrudService();

    public static void main(String[] args) {
        Flyway.configure()
                .dataSource(resourceBundle.getString(JDBC_URL), null, null)
                .load()
                .migrate();

        ticketCrudServiceCheck();

        ticketCrudServiceNullCheck();

        HibernateUtil.getInstance().close();
    }

    private static void ticketCrudServiceCheck() {
        System.out.println(ticketCrudService.getById(11));
        ticketCrudService.update(clientCrudService.getById(7), 11);
        ticketCrudService.delete(8);
        System.out.println(ticketCrudService.getTickets());
    }

    private static void ticketCrudServiceNullCheck() {
        ticketCrudService.create(null);

        ticketCrudService.create(null);
    }
}
