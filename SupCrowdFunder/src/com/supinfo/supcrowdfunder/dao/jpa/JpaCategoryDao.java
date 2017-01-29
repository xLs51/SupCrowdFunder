package com.supinfo.supcrowdfunder.dao.jpa;

import com.supinfo.supcrowdfunder.dao.CategoryDao;
import com.supinfo.supcrowdfunder.entity.Category;

public class JpaCategoryDao extends AbstractJpaDao<Category> implements CategoryDao
{
	public JpaCategoryDao()
	{
		super(Category.class);
	}
}