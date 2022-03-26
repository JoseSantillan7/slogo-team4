# Collections API Lab Discussion
### Jose Santillan, Ritvik Janamsetty, Kushagra Ghosh, Robert Cranston
### 4

## In your experience using these collections, are they hard or easy to use?

In my experience, using the Collections API was extremely simple, intuitive and flexible.

## In your experience using these collections, do you feel mistakes are easy to avoid?

Not frequently as the methods are pretty simple and the naming conventions are pretty intuitive.

## What methods are common to all collections (except Maps)?
boolean
add(E e)
Ensures that this collection contains the specified element (optional operation).

boolean
addAll(Collection<? extends E> c)
Adds all of the elements in the specified collection to this collection (optional operation).

void
clear()
Removes all of the elements from this collection (optional operation).

boolean
contains(Object o)
Returns true if this collection contains the specified element. 

boolean
containsAll(Collection<?> c)
Returns true if this collection contains all of the elements in the specified collection.

boolean
isEmpty()
Returns true if this collection contains no elements.

abstract Iterator<E>
iterator()
Returns an iterator over the elements contained in this collection.

boolean
remove(Object o)
Removes a single instance of the specified element from this collection, if it is present (optional operation).

boolean
removeAll(Collection<?> c)
Removes all of this collection's elements that are also contained in the specified collection (optional operation).

boolean
retainAll(Collection<?> c)
Retains only the elements in this collection that are contained in the specified collection (optional operation).

Object[]
toArray()
Returns an array containing all of the elements in this collection.

T[]
toArray(T[] a)
Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
String
toString()
Returns a string representation of this collection.


## What methods are common to all Deques?
boolean
add(E e)
Inserts the specified element into the queue represented by this deque (in other words, at the tail of this deque) if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available.

boolean
addAll(Collection<? extends E> c)
Adds all of the elements in the specified collection at the end of this deque, as if by calling addLast(E) on each one, in the order that they are returned by the collection's iterator.

void
addFirst(E e)
Inserts the specified element at the front of this deque if it is possible to do so immediately without violating capacity restrictions, throwing an IllegalStateException if no space is currently available.

void
addLast(E e)
Inserts the specified element at the end of this deque if it is possible to do so immediately without violating capacity restrictions, throwing an IllegalStateException if no space is currently available.

boolean
contains(Object o)
Retrieves and removes the head of the queue represented by this deque (in other words, the first element of this deque), or returns null if this deque is empty.

E
pollFirst()
Retrieves and removes the first element of this deque, or returns null if this deque is empty.

E
pollLast()
Retrieves and removes the last element of this deque, or returns null if this deque is empty.

E
pop()
Pops an element from the stack represented by this deque.

void
push(E e)
Pushes an element onto the stack represented by this deque (in other words, at the head of this deque) if it is possible to do so immediately without violating capacity restrictions, throwing an IllegalStateException if no space is currently available.

E
remove()
Retrieves and removes the head of the queue represented by this deque (in other words, the first element of this deque).

boolean
remove(Object o)
Removes the first occurrence of the specified element from this deque.

E
removeFirst()
Retrieves and removes the first element of this deque.

boolean
removeFirstOccurrence(Object o)
Removes the first occurrence of the specified element from this deque.

E
removeLast()
Retrieves and removes the last element of this deque.

boolean
removeLastOccurrence(Object o)
Removes the last occurrence of the specified element from this deque.

int
size()
Returns the number of elements in this deque.


## What is the purpose of each interface implemented by LinkedList?

Serializable: A marker interface that allows a linked list to be seralized to an array of bytes
Cloneable: Allows the linked list to be cloned with ease
Iterable<E>: Allows the linked list to be iterated through using a for each loop.
Collection<E>: Allows the linked list to have the basic collection methods such as adding, removing, and methods to access the state of the linked list.
Deque<E>: Allows the linked list to process additions and deletions at both ends of the linked list.
List<E>: Allows for ordered and indexable operations
Queue<E>: Implements LIFO data structures operations.

## How many different implementations are there for a Set?

There are 3 different types of implementations, HashSets, TreeSets, and a LinkedHashSet.

## What is the purpose of each superclass of PriorityQueue?

* AbstractQueue - ability to add and remove elements
* AbstractCollection - ability to check what is in the collection, and display its contents as a string
* Object -  gives access to object methods such as clone, and equals and thread handling methods such as notify and notify all
* Collections - give access to collection specific features such as the ability to convert to an array and  give a hashcode.

## What is the purpose of the collection utility classes?

The collection utility classes are used for their static methods that does operations on collections
and/or return them. Example of these static methods are Collections.sort(List myList, Comparator c) and
Collections.copy(List destination, List src).

## API Characterics applied to Collections API

* Easy to learn

* Provides for extension

* Leads to readable code

* Hard to misuse