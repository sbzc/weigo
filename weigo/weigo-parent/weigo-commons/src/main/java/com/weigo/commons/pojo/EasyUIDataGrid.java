package com.weigo.commons.pojo;

import java.util.List;

public class EasyUIDataGrid {
	//当前页显示数据
		private List<?> rows;
		//总条数
		private long total;
		public List<?> getRows() {
			return rows;
		}
		public void setRows(List<?> rows) {
			this.rows = rows;
		}
		public long getTotal() {
			return total;
		}
		public void setTotal(long total) {
			this.total = total;
		}
}
