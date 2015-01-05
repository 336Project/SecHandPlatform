<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<li class="nav-header">系统信息</li>
		<li><a href="widgets.html"><i class="icon-group"></i>用户管理</a></li>
		<li><a href="widgets.html"><i class="icon-group"></i>角色管理</a></li>
		<!-- <li  class="active">
			<a href="index.html"><i class="icon-dashboard"></i>用户管理 </a>
		</li> -->
		<!-- <li>
			<a class="dropdown" href="#"><i class="icon-tint"></i> 一级菜单 <span class="label">2</span></a>
			<ul>
				<li><a href="buttons.html"><i class="icon-bullhorn"></i> 二级菜单</a></li>
				<li><a href="slidersnprogress.html"><i class="icon-minus"></i> 二级菜单 </a></li>
			</ul>	
		</li> -->
		<!-- Sidebar header @add class nav-header for sidebar header -->
		<li class="nav-header">菜单分类</li>
		<li><a href="calendar.html"><i class="icon-calendar"></i>一级菜单</a></li>
		<li><a href="gallery.html"><i class="icon-picture"></i>一级菜单</a></li>
		<li><a href="login.html"><i class="icon-signin"></i>一级菜单</a></li>
		<li>
			<a class="dropdown" href="#"><i class="icon-user"></i> 一级菜单 <span class="label">2</span></a>
			<ul>
				<li><a href="profile.html"><i class="icon-usre"></i> 二级菜单 </a></li>
				<li><a href="profileTwo.html"><i class="icon-usre"></i> 二级菜单 </a></li>
			</ul>	
		</li>
		<li> 
			<a class="dropdown" href="#"><i class="icon-folder-close-alt"></i> 一级菜单 menu <span class="label">3</span></a>
			<ul>
				<li><a href="#"><i class="icon-hdd"></i> 二级菜单 </a></li>
				<li><a href="#"><i class="icon-coffee"></i> 二级菜单 </a></li>
				<li><a href="#"><i class="icon-crop"></i> 二级菜单 </a></li>
			</ul>	
		</li>
		<li class="nav-header">菜单分类</li>
		<li><a href="tables.html"><i class="icon-table"></i>一级菜单</a></li>
		<li> <!-- Example for second level menu -->
			<a class="dropdown" href="#"><i class="icon-folder-close-alt"></i> 一级菜单 <span class="label">3</span></a>
			<ul>
				<li><a href="nvd.html"><i class="icon-hdd"></i> 二级菜单</a></li>
				<li><a href="flot.html"><i class="icon-coffee"></i>二级菜单</a></li>
				<li><a href="knobs.html"><i class="icon-coffee"></i>二级菜单</a></li>
			</ul>	
		</li>
		<li><a href="typography.html"><i class="icon-text-width"></i>一级菜单</a></li>
		
		<li><a href="icons.html"><i class="icon-truck"></i>一级菜单</a></li>