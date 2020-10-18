package propets.service;

import propets.dto.Post;

public interface IFavouritesActivitiesService {
	Post[] getFavouritesMessagesPosts(String login);
}
