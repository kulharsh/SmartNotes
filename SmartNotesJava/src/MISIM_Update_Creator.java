import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Harshad
 * Date: 1/15/16
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class MISIM_Update_Creator
{
    public static void main(String args[]) throws IOException, SolrServerException {
        HttpSolrClient solr = new HttpSolrClient("http://localhost:8983/solr/collection1");
        SolrQuery q = new SolrQuery();
        q.setQuery("*:*");
        q.setFields("id","content","score","title");
        q.setStart(0);
        QueryResponse response = solr.query(q);
        SolrDocumentList result = response.getResults();
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++)
        {
            System.out.println(result.get(i).getFieldValue("title"));
            String s = (String) result.get(i).getFieldValue("content");
            System.out.println(s);
            //System.out.println("*****************************");
        }

    }
}
