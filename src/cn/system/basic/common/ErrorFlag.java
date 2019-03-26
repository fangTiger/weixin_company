package cn.system.basic.common;

public class ErrorFlag {
	/*
	 * 操作失败
	 */

	public final static int OPR_FAIL = 0;
	/*
	 * 后台异常
	 */
	public final static int MANAGE_ERROR = -1;

	/*
	 * 传入参数为空
	 */
	public final static int PARAMETER_NULL = -3;

	/*
	 * 根据id查询时 -4 对象为null
	 */
	public final static int FINDBYID_NULL = -4;

	/*
	 * session 失效
	 */
	public final static int SESSION_FAIL = -5;

	/* 已经存在 */
	public final static int EXIST = -6;
	
	/* 错误的地址 */
	public final static String ERROR_PATH = "error path";

}
