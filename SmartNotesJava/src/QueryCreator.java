import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Harshad
 * Date: 12/19/15
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryCreator {
    public static void main(String args[]) throws IOException, SolrServerException {
        HttpSolrClient solr = new HttpSolrClient("http://localhost:8983/solr/SN_trial");
        SolrQuery q = new SolrQuery();
        q.setQuery("query");
        q.setFields("attr_stream_source_info","attr_content","score");
        q.setStart(0);
        QueryResponse response = solr.query(q);
        SolrDocumentList result = response.getResults();
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++)
        {
            System.out.println(result.get(i).getFieldValue("score"));
            System.out.println(result.get(i).getFieldValue("attr_stream_source_info"));
            System.out.println("*****************************");
        }

    }
}
