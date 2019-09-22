package th.ku.bookstore;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class BookDaoImp implements BookDao {


    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String query = "INSERT INTO bookstore (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice() };
        jdbcTemplate.update(query, data);
    }

    @Override
    public void update(int id, Book book) {
        String query = "UPDATE Bookstore SET id = ?, name = ?, price = ? WHERE id = ?;";
        Object[] args = new Object[]{book.getId(), book.getName(), book.getPrice(), id};
        jdbcTemplate.update(query, args);
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM Bookstore WHERE id = ?;";
        Object[] args = new Object[]{id};
        jdbcTemplate.update(query, args);
    }

    @Override
    public Book findById(int id) {
        String query = "SELECT * FROM Bookstore WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(query, args, new BookRowMapper());
    }

    public List<Book> findAll() {
        String query = "SELECT * FROM bookstore";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;
    }




}
