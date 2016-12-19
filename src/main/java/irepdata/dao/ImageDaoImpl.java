package irepdata.dao;

import irepdata.model.Image;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Gvozd on 13.12.2016.
 */
@Repository
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void createImage(Image image) {
        sessionFactory.getCurrentSession().saveOrUpdate(image);
    }

    @Override
    public Image getImage(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select distinct im from Image im where im.id = :id").setParameter("id", id);
        Image image = (Image) query.uniqueResult();
        return image;
    }

    @Override
    public void updateImage(Long id, boolean publicity) {
        String hql = "UPDATE Image set " +
                "publicity = :publicity " +
                "WHERE id = :image_id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("publicity", publicity);
        query.setParameter("image_id", id);
        query.executeUpdate();
    }

    @Override
    public List<Image> getImages(Long pagination) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Image.class);
        criteria.setMaxResults(Image.MAXIMAGESSHOWINGCAPACITY);
        criteria.setFirstResult(Math.toIntExact(pagination));
        criteria.add( Restrictions.eq("publicity", true));
        criteria.setReadOnly(true);
        List<Image> result = criteria.list();
        return result;
    }

    @Override
    public Long getImageCount() {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(*) from Image");
        Long count = (Long)query.uniqueResult();
        return count;
    }
}
