package by.VIWARR.CRUD.DAO;


import by.VIWARR.CRUD.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //вывод всех продуктов
    @Transactional(readOnly = true) //т.к. мы только читаем данные из БД, помечаем readOnly
    public List<Product> getProductList() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Product", Product.class)
                .getResultList();
    }

    //отображение продукта по id
    @Transactional(readOnly = true)
    public Product showProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    //добавление продукта в БД
    @Transactional
    public void saveProduct(String nameProduct) {
        Session session = sessionFactory.getCurrentSession();
        Product product = new Product(nameProduct);
        session.persist(product);
    }

    //обновление продукта по Id
    @Transactional
    public void updateProductById(int id, Product product) {
        Session session = sessionFactory.getCurrentSession();
        Product productToBeUpdate = session.get(Product.class, id);

        productToBeUpdate.setName(product.getName());
    }

    @Transactional
    public void deleteProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        session.delete(product);
    }
}
