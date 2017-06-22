/********************************************************
 * File: tree.h                                       *
 * Author: Jose Salinas                                 *
 ********************************************************/
enum NodeKind {leaf, nonleaf};
struct Node
{
    NodeKind kind;
    char     ch;
    Node*    left;
    Node*    right;
    
    Node(char c)
    {
        kind = leaf;
        ch   = c;
    }
    Node(Node* L, Node *R)
    {
        kind  = nonleaf;
        left  = L;
        right = R;
    }
};
