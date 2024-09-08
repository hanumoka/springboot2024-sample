import { gql, useApolloClient, useMutation, useSubscription } from "@apollo/client";
import { useEffect, useState } from "react";

// Mutation 정의
const CREATE_BOARD_POST = gql`
    mutation CreateBoardPost($title: String!, $content: String!, $authorId: Int!) {
        createBoardPost(title: $title, content: $content, authorId: $authorId) {
            id
            title
            content
        }
    }
`;

// Subscription 정의
const NEW_BOARD_POST_SUBSCRIPTION = gql`
    subscription MySubscription {
        newBoardPost {
            content
            id
            title
        }
    }
`;

const Board = () => {
    const [boards, setBoards] = useState([]);
    const [newBoards, setNewBoards] = useState([]); // 구독으로 받은 새 게시글을 위한 상태
    const [newBoard, setNewBoard] = useState({
        authorId: 1,
        title: "",
        content: "",
    });

    const client = useApolloClient();
    const [createBoardPost] = useMutation(CREATE_BOARD_POST);

    useEffect(() => {
        client
        .query({
            query: gql`
                {
                    getBoards {
                        id
                        title
                        content
                    }
                }
            `,
        })
        .then((results) => setBoards(results.data.getBoards));
    }, [client]);

    // Subscription 처리
    const { data: subscriptionData } = useSubscription(NEW_BOARD_POST_SUBSCRIPTION);
    useEffect(() => {
        if (subscriptionData && subscriptionData.newBoardPost) {
            console.log("New board post received:", subscriptionData.newBoardPost);
            setNewBoards(prevNewBoards => [subscriptionData.newBoardPost, ...prevNewBoards]);
        }
    }, [subscriptionData]);

    const handleCreateBoard = () => {
        createBoardPost({
            variables: {
                title: newBoard.title,
                content: newBoard.content,
                authorId: newBoard.authorId
            }
        })
            .then((result) => {
                const createdBoard = result.data.createBoardPost;
                setBoards(prevBoards => [createdBoard, ...prevBoards]);
                setNewBoard({ authorId: 1, title: "", content: "" });
            })
            .catch((error) => {
                console.error("Error creating board post:", error);
            });
    };

    return (
        <div>
            <h1>Board Posts</h1>
            <div>
                <input
                    type="text"
                    value={newBoard.title}
                    placeholder="제목"
                    onChange={(e) => setNewBoard({...newBoard, title: e.target.value})}
                />
                <input
                    type="text"
                    value={newBoard.content}
                    placeholder="내용"
                    onChange={(e) => setNewBoard({...newBoard, content: e.target.value})}
                />
                <button onClick={handleCreateBoard}>등록</button>
            </div>

            <h2>New Posts (from Subscription)</h2>
            <ul>
                {newBoards.map((board) => (
                    <li key={board.id}>{board.title} - {board.content}</li>
                ))}
            </ul>

            <h2>All Posts</h2>
            <ul>
                {boards.map((board) => (
                    <li key={board.id}>{board.title} - {board.content}</li>
                ))}
            </ul>
        </div>
    );
};

export default Board;