package net.forixaim.efm_ex.util;

public class ListSizeMismatchException extends Exception
{
	public int size1;
	public int size2;

	public ListSizeMismatchException()
	{

	}

	public ListSizeMismatchException(final int size1, final int size2)
	{
		this.size1 = size1;
		this.size2 = size2;
	}

	@Override
	public String toString()
	{
		return "ListSizeMismatchException(" + size1 + " from list 1 is not the same as " + size2 +" from list 2)";


	}
}
