# REST API Testing Sample Description

## Project main files

* SampleTestCases - main class that contains all the necessary test functions
* Post - class that repeats single resource structure
* APIService - interface that contains commands (requests) for server
* pom.xml - Maven dependencies file

## Task 1. Create and automate test cases

:white_check_mark: Getting a list of all resources (`testGetPostList()`)  
:white_check_mark: Getting a resource by id (`testGetPostById()`)  
:negative_squared_cross_mark: Filtering by query parameters (I honestly don't understand what I have to do here)

## Task 2. Create a bug report for the following issue:

**The request GET /posts/0 returned 404 Not Found, but you expect an empty list with 200 OK.**

This was made particular, you may see the output of bug report with `testGetPost0Answer()` function execution (in that task I also probably didn't understand what output data have I to show).

## Task 3. CREATE, UPDATE and DELETE operations testing 

### CREATE

To realize that method we need to create method for it inside the `APIService` interface:

```java
@POST("posts/new")
Call<Post> createNewPost(
        @Field("userId") Integer userId,
        @Field("title") String title,
        @Field("body") String body,
);
```
Then create a method in `SampleTestCases` class:

```java
@Test
public void testCreatePost(){

    //Create mandatory fields
    Integer userId = 100;
    String title = "MyTitle";
    String body = "MyBody";
    
    this.call = this.apiService.createNewPost(userId, title, body);
    this.response = this.call.execute();
    
    int necessaryCode = 200;
    Assert.assertEquals("Something goes wrong.", necessaryCode, this.response.code());
}
```
If the post was successful `created this.response.code()` will return us status code 200.

### UPDATE

Add the method into the interface:

```java
@PATCH("posts/{id}")
Call<Post> updatePost(
        @Path("id") Integer id,
        @Part("userId") Integer userId,
        @Part("title") String title,
        @Part("body") String body,
);

```

We may also use `@PUT` annotation instead of `@PATCH`, in that case the Post will be completely replaced.

The next step is to create test method:

```java
@Test
public void testUpdatePost(){
    
    Integer id = 1;
    Integer userId = 100;
    String title = "MyTitle";
    String body = "MyBody";
    
    this.call = this.apiService.updatePost(id, userId, title, body);
    this.response = this.call.execute();

    int necessaryCode = 200;
    Assert.assertEquals("Something goes wrong.", necessaryCode, this.response.code());
}
```

### DELETE

As in cases above create interface method:

```java
@DELETE("posts/{id}")
Call<Void> deletePost(
            @Path("id") Integer id
);
```

And test method:

```java
@Test
public void testDeletePost(){
    this.call = this.apiService.deletePost(id);
    this.response = this.call.execute();

    int necessaryCode = 200;
    Assert.assertEquals("Something goes wrong.", necessaryCode, this.response.code());
}
```