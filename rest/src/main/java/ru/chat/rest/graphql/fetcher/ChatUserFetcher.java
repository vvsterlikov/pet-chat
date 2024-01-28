package ru.chat.rest.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import ru.chat.core.data.entity.ChatUserEntity;
import ru.chat.core.data.repository.ChatUserRepository;

@DgsComponent
@AllArgsConstructor
public class ChatUserFetcher {

    private final ChatUserRepository chatUserRepository;

    @DgsData(parentType = "QueryResolver", field = "chatuser")
    public ChatUserEntity findById(@InputArgument("id") Long id, DataFetchingEnvironment dfe) {
        return chatUserRepository.findById(id).get();
    }

}
