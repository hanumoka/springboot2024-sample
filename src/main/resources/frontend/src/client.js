import { ApolloClient, InMemoryCache, HttpLink, split } from '@apollo/client';
import { getMainDefinition } from '@apollo/client/utilities';
import { GraphQLWsLink } from '@apollo/client/link/subscriptions';
import { createClient } from 'graphql-ws';

// HTTP 연결 설정
const httpLink = new HttpLink({
    uri: 'http://localhost:8080/graphql'
});

// WebSocket 연결 설정
const wsLink = new GraphQLWsLink(createClient({
    url: 'ws://localhost:8080/graphql',
    // 옵션: 연결 파라미터, 재연결 로직 등을 여기에 추가할 수 있습니다.
}));

// HTTP와 WebSocket 연결을 분리
const splitLink = split(
    ({ query }) => {
        const definition = getMainDefinition(query);
        return (
            definition.kind === 'OperationDefinition' &&
            definition.operation === 'subscription'
        );
    },
    wsLink,
    httpLink
);

// Apollo Client 생성
const client = new ApolloClient({
    link: splitLink,
    cache: new InMemoryCache()
});

export default client;