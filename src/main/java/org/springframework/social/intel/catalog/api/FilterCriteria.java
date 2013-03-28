package org.springframework.social.intel.catalog.api;

import java.util.ArrayList;

//GET https://api.intel.com/catalog/v1/books?$fields=Name,Description
//https://api.intel.com/catalog/v1/books?$limit=100&$offset=101

//For sorting output, use {$orderby} clause.
//Syntax: $orderby=field:[asc|desc] *( , field:[asc|desc])
//https://api.intel.com/catalog/v1/{item_type}/{query}
//
//For simple filtering, use {$filter} clause.
//Syntax: $filter=field1 op1 'value1' *( logicalOp field2 op2 'value2')

//'op' can be one of the available operations in the following table:
//	Eq Equal /books?$filter=Name eq 'The Godfather'
//	Ne Not equal /books?$filter=Name ne 'The Godfather'
//	Gt Greater than /books?$filter=Year gt '1950
//	Ge Greater than or equal /books?$filter=Year ge '1980'
//	Lt Less than /books?$filter=Year lt '2000'
//	Le Less than or equal /books?$filter=Year le '1980'

//'logicalOp' is one from the following table:
//And Logical and /books?$filter=Year le '2000' and Name eq 'The Godfather'
//Or Logical or /books?$filter=Year le '2000' or Year gt '1950'
//Not Logical negation /books?$filter=not (Year gt '2000')

public class FilterCriteria {
	private StringBuilder filter = new StringBuilder();

	private ArrayList<String[]> orderBy = new ArrayList<String[]>();

	private StringBuilder fields = new StringBuilder();

	private int offset = -1;

	private int lim = -1;

	public static FilterCriteria get() {
		return new FilterCriteria();
	}
	
	private FilterCriteria andOr(boolean and) {
		if (filter.length() != 0) {
			if (and) {
				filter.append(" and ");
			} else {
				filter.append(" or ");
			}
		}
		return this;
	}

	public FilterCriteria eq(boolean and, String name, String value) {
		andOr(and);
		filter.append(name).append(" eq ").append(value);
		return this;
	}

	public FilterCriteria ne(boolean and, String name, String value) {
		andOr(and);
		filter.append(name).append(" ne ").append(value);
		return this;
	}

	public FilterCriteria lt(boolean and, String name, String value) {
		andOr(and);
		filter.append(name).append(" lt ").append(value);
		return this;
	}

	public FilterCriteria gt(boolean and, String name, String value) {
		andOr(and);
		filter.append(name).append(" gt ").append(value);
		return this;
	}

	public FilterCriteria le(boolean and, String name, String value) {
		andOr(and);
		filter.append(name).append(" le ").append(value);
		return this;
	}

	public FilterCriteria ge(boolean and, String name, String value) {
		andOr(and);
		filter.append(name).append(" ge ").append(value);
		return this;
	}

	public FilterCriteria group() {
		filter.insert(0, '(').append(')');
		return this;
	}

	public FilterCriteria not() {
		group();
		filter.insert(0, '!');
		return this;
	}

	public FilterCriteria field(String name) {
		if (fields.length() > 0) {
			fields.append(',');
		}

		fields.append(name);
		return this;
	}

	public FilterCriteria sAsc(String name) {
		this.orderBy.add(new String[] { name, "asc" });
		return this;
	}

	public FilterCriteria sDsc(String name) {
		this.orderBy.add(new String[] { name, "desc" });
		return this;
	}

	public FilterCriteria limit(int number) {
		this.lim = number;
		return this;
	}

	public FilterCriteria offset(int offset) {
		this.offset = offset;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (fields.length() > 0) {
			ampl(sb);
			sb.append("$field=").append(fields);
		}

		if (filter.length() > 0) {
			ampl(sb);
			sb.append("$filter=").append(filter);
		}

		int size = orderBy.size();
		if (size > 0) {
			ampl(sb);

			sb.append("$OrderBy=");

			for (int i = 0; i < size; i++) {
				sb.append(orderBy.get(i)[0]).append(" ");
				sb.append(orderBy.get(i)[1]);
				if (i + 1 < size) {
					sb.append(',');
				}
			}
		}

		if (lim != -1) {
			ampl(sb);
			sb.append("$limit=").append(lim);
		}

		if (offset != -1) {
			ampl(sb);
			sb.append("$offset=").append(offset);
		}
		return sb.toString();
	}

	private void ampl(StringBuilder sb) {
		if (sb.length() > 0) {
			sb.append('&');
		}
	}
}
