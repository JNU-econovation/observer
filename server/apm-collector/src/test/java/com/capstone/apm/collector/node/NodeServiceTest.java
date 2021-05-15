package com.capstone.apm.collector.node;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NodeServiceTest {

    @Autowired
    private NodeService nodeService;

    @Test
    public void test() {
        String nodeId = nodeService.saveTransaction(createTransactionDto("test_server", "test_addr1"));
        Node node = nodeService.findByNodeId(nodeId);

        assertEquals(1, node.getAgents().size());
        assertEquals(1, node.getAgents().get(0).getTransactions().size());

        nodeService.saveTransaction(createTransactionDto("test_server", "test_addr1"));
        node = nodeService.findByNodeId(nodeId);

        assertEquals(1, node.getAgents().size());
        assertEquals(2, node.getAgents().get(0).getTransactions().size());


        nodeService.saveTransaction(createTransactionDto("test_server", "test_addr2"));
        node = nodeService.findByNodeId(nodeId);

        assertEquals(2, node.getAgents().size());
        assertEquals(1, node.getAgents().get(1).getTransactions().size());


        String nodeId2 = nodeService.saveTransaction(createTransactionDto("test_server1", "test_addr2"));
        Node node2 = nodeService.findByNodeId(nodeId2);

        assertEquals(1, node2.getAgents().size());
        assertEquals(1, node2.getAgents().get(0).getTransactions().size());
    }


    private TransactionDto createTransactionDto(String serverName,String addr) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setServerName(serverName);
        transactionDto.setAddr(addr);
        transactionDto.setTransactionStatus("success");
        transactionDto.setRemoteAddr("요청 받는 서버 주소");
        transactionDto.setStartTransactionTime(1000);
        transactionDto.setEndTransactionTime(2000);
        transactionDto.setTraceId("1");
        transactionDto.setRemoteServerType("Server");
        transactionDto.setStatusCode(200);
        transactionDto.setSequence(1);
        return transactionDto;
    }
}