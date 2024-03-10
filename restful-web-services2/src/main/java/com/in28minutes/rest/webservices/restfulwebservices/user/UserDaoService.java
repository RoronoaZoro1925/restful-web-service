package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService 
{
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;
	
	static 
	{
		users.add(new User(++usersCount,"Luffy",LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Zoro",LocalDate.now().minusYears(45)));
		users.add(new User(++usersCount,"Sanji",LocalDate.now().minusYears(42)));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public List<User> findById()
	{
		return users;
	}
	
	public User findOne(int id)
	{
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id)
	{
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
	
	public User save(User user) 
	{
		user.setId(++usersCount);
		users.add(user);//Yash bhai se puchna hai
		return user;
	}

}