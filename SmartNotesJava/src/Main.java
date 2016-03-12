import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SolrServerException {
        HttpSolrClient client = new HttpSolrClient("http://localhost:8983/solr/SN_trial");
        ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");
        File dir = new File("E:\\MS Sem 1\\IR\\PDFs");

        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child
                up.addFile(child,"PDF");
                //up.setParam("literal.id", child.getName());
                up.setParam("uprefix","attr_");
                up.setParam("fmap.content","attr_content");
                System.out.println("Indexing : "+child.getName());
            }
        }
        // up.addFile(new File("E:\\MS Sem 1\\IR\\CSE535Lec1.pdf"),"PDF");
        up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
        NamedList<Object> abc = client.request(up);
        if(abc == null)
            System.out.print("Something is wrong");
        else
            System.out.print("This is running!");
        /*Solr
        SolrInputDocument d = new SolrInputDocument();
        d.addField("abc","pqr");
        client.add(d);
        client.commit();    */
        client.close();
    }
}
