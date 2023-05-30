package kg.ecommerce.priceservice.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import kg.ecommerce.priceservice.models.elastic.ElasticPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "prices-index";


    public String createOrUpdateDocument(ElasticPrice product) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(product.getId())
                .document(product)
        );
        if(response.result().name().equals("Created")){
            return new StringBuilder("Document has been successfully created.").toString();
        }else if(response.result().name().equals("Updated")){
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public ElasticPrice getDocumentById(String productId) throws IOException{
        ElasticPrice product = null;
        GetResponse<ElasticPrice> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(productId),
                ElasticPrice.class
        );

        if (response.found()) {
            product = response.source();
            System.out.println("ElasticPrice sku " + product.getSku());
        } else {
            System.out.println ("ElasticPrice not found");
        }

        return product;
    }

    public String deleteDocumentById(String productId) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("ElasticPrice with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("ElasticPrice not found");
        return new StringBuilder("ElasticPrice with id " + deleteResponse.id()+" does not exist.").toString();

    }

    public  List<ElasticPrice> searchAllDocuments() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, ElasticPrice.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticPrice> products = new ArrayList<>();
        for(Hit object : hits){

            System.out.print(((ElasticPrice) object.source()));
            products.add((ElasticPrice) object.source());

        }
        return products;
    }
}
