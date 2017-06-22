#ifndef EVENT_H
#define EVENT_H
struct Event
{
    int vertexOne;
    int vertexTwo;
    double time;
    
    Event(int u, int v, double t)
    {
        vertexOne = u;
        vertexTwo = v;
        time = t;
    }
    
    Event()
    {
        vertexOne = 0;
        vertexTwo = 0;
        time = 0.0;
    }
};
#endif
