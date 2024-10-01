
-- Crear la base de datos PROCEDIMIENTOS
CREATE DATABASE PROCEDIMIENTOS;
GO

USE [PROCEDIMIENTOS]
GO
/****** Object:  Table [dbo].[cita]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cita](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idPaciente] [int] NULL,
	[nroCuenta] [varchar](20) NOT NULL,
	[fecha] [date] NOT NULL,
	[horaInicio] [time](7) NOT NULL,
	[horaFin] [time](7) NOT NULL,
	[idProgramacion] [bigint] NOT NULL,
	[estado] [varchar](20) NOT NULL,
	[idMedico] [int] NOT NULL,
	[usuarioCreador] [varchar](50) NOT NULL,
	[fechaRegistro] [datetime] NOT NULL,
	[esAdicional] [bit] NOT NULL,
	[financiamiento] [nvarchar](255) NULL,
	[idProcedimiento] [int] NULL,
	[diagnostico] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[empleado]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[empleado](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[departamento] [varchar](100) NOT NULL,
	[puesto] [varchar](100) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[telefono] [varchar](15) NULL,
	[email] [varchar](100) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[medico]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[medico](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[telefono] [varchar](15) NULL,
	[email] [varchar](100) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[procedimiento]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[procedimiento](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[id_servicio] [int] NOT NULL,
	[codigo] [varchar](255) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[programacion]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[programacion](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha] [date] NOT NULL,
	[hora_inicio] [time](7) NOT NULL,
	[hora_fin] [time](7) NOT NULL,
	[tiempo_promedio] [bigint] NULL,
	[fecha_registro] [datetime] NOT NULL,
	[usuario_creador] [varchar](255) NOT NULL,
	[id_medico] [int] NOT NULL,
	[id_procedimiento] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rol]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rol](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[codigo] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RoleServicio]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoleServicio](
	[roleId] [int] NOT NULL,
	[servicioId] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[roleId] ASC,
	[servicioId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[servicio]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[servicio](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[descripcion] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usuario]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK863n1y3x0jalatoir4325ehal] UNIQUE NONCLUSTERED
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UsuarioRol]    Script Date: 27/08/2024 0:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsuarioRol](
	[userId] [int] NOT NULL,
	[roleId] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[userId] ASC,
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[cita]  WITH CHECK ADD FOREIGN KEY([idMedico])
REFERENCES [dbo].[medico] ([id])
GO
ALTER TABLE [dbo].[cita]  WITH CHECK ADD FOREIGN KEY([idProgramacion])
REFERENCES [dbo].[programacion] ([id])
GO
ALTER TABLE [dbo].[cita]  WITH CHECK ADD  CONSTRAINT [FK_Cita_Procedimiento] FOREIGN KEY([idProcedimiento])
REFERENCES [dbo].[procedimiento] ([id])
GO
ALTER TABLE [dbo].[cita] CHECK CONSTRAINT [FK_Cita_Procedimiento]
GO
ALTER TABLE [dbo].[empleado]  WITH CHECK ADD FOREIGN KEY([id_user])
REFERENCES [dbo].[usuario] ([id])
GO
ALTER TABLE [dbo].[medico]  WITH CHECK ADD FOREIGN KEY([id_user])
REFERENCES [dbo].[usuario] ([id])
GO
ALTER TABLE [dbo].[procedimiento]  WITH CHECK ADD FOREIGN KEY([id_servicio])
REFERENCES [dbo].[servicio] ([id])
GO
ALTER TABLE [dbo].[programacion]  WITH CHECK ADD FOREIGN KEY([id_medico])
REFERENCES [dbo].[medico] ([id])
GO
ALTER TABLE [dbo].[programacion]  WITH CHECK ADD  CONSTRAINT [FK_ProcedimientoProgramacion] FOREIGN KEY([id_procedimiento])
REFERENCES [dbo].[procedimiento] ([id])
GO
ALTER TABLE [dbo].[programacion] CHECK CONSTRAINT [FK_ProcedimientoProgramacion]
GO
ALTER TABLE [dbo].[RoleServicio]  WITH CHECK ADD FOREIGN KEY([roleId])
REFERENCES [dbo].[rol] ([id])
GO
ALTER TABLE [dbo].[RoleServicio]  WITH CHECK ADD FOREIGN KEY([servicioId])
REFERENCES [dbo].[servicio] ([id])
GO
ALTER TABLE [dbo].[UsuarioRol]  WITH CHECK ADD FOREIGN KEY([roleId])
REFERENCES [dbo].[rol] ([id])
GO
ALTER TABLE [dbo].[UsuarioRol]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[usuario] ([id])
GO

-- Insertar datos en la tabla usuario
SET IDENTITY_INSERT [dbo].[usuario] ON
GO
INSERT [dbo].[usuario] ([id], [username], [password]) VALUES (3, N'admision', N'$2a$12$tx2bPe62LJw4W93yV86zy.i.EF6HzhOiG4O0iskCWM89tVjarMdRK')
GO
INSERT [dbo].[usuario] ([id], [username], [password]) VALUES (1, N'administrador', N'$2a$12$tx2bPe62LJw4W93yV86zy.i.EF6HzhOiG4O0iskCWM89tVjarMdRK')
GO
INSERT [dbo].[usuario] ([id], [username], [password]) VALUES (1003, N'medicog', N'$2a$12$blNS7.2IMsi1YNpmBhbSyeBskqUPg.GZvPrpMZ1SGmwSuFAD7Wji6')
GO
INSERT [dbo].[usuario] ([id], [username], [password]) VALUES (1004, N'medicod', N'$2a$12$blNS7.2IMsi1YNpmBhbSyeBskqUPg.GZvPrpMZ1SGmwSuFAD7Wji6')
GO
SET IDENTITY_INSERT [dbo].[usuario] OFF
GO

-- Insertar datos en la tabla medico
SET IDENTITY_INSERT [dbo].[medico] ON
GO
INSERT [dbo].[medico] ([id], [id_user], [nombre], [apellido], [telefono], [email]) VALUES (1003, 1003, N'Medico Gastroenterologo', N'Gastroenterólogo', N'95682312', N'example@example.com')
GO
INSERT [dbo].[medico] ([id], [id_user], [nombre], [apellido], [telefono], [email]) VALUES (1004, 1004, N'Medico Dermatologia', N'Dermatologia', N'123456789', N'medicog@example.com')
GO
SET IDENTITY_INSERT [dbo].[medico] OFF
GO

-- Insertar datos en la tabla rol
SET IDENTITY_INSERT [dbo].[rol] ON
GO
INSERT [dbo].[rol] ([id], [nombre], [codigo]) VALUES (5, N'Admisionista', N'ADMISION')
GO
INSERT [dbo].[rol] ([id], [nombre], [codigo]) VALUES (1004, N'Medico Gastroenterelogo', N'MEDICO')
GO
INSERT [dbo].[rol] ([id], [nombre], [codigo]) VALUES (1005, N'Medico Dermatologo', N'MEDICO')
GO
INSERT [dbo].[rol] ([id], [nombre], [codigo]) VALUES (1, N'Administrador', N'ADMIN')
GO
SET IDENTITY_INSERT [dbo].[rol] OFF
GO


-- Insertar datos en la tabla UsuarioRol
INSERT [dbo].[UsuarioRol] ([userId], [roleId]) VALUES (1, 1) -- Asignar rol ADMIN al usuario administrador
GO
INSERT [dbo].[UsuarioRol] ([userId], [roleId]) VALUES (3, 5)
GO
INSERT [dbo].[UsuarioRol] ([userId], [roleId]) VALUES (1003, 1004)
GO
INSERT [dbo].[UsuarioRol] ([userId], [roleId]) VALUES (1004, 1005)
GO

-- Insertar datos en la tabla servicio
SET IDENTITY_INSERT [dbo].[servicio] ON
GO
INSERT [dbo].[servicio] ([id], [nombre], [descripcion]) VALUES (1, N'Cardiologia', N'Servicio de Cardiologia')
GO
INSERT [dbo].[servicio] ([id], [nombre], [descripcion]) VALUES (2, N'Otorrino', N'Servicio de Otorrino')
GO
INSERT [dbo].[servicio] ([id], [nombre], [descripcion]) VALUES (3, N'Gastroenterología', N'Servicio de Gastroenterología')
GO
INSERT [dbo].[servicio] ([id], [nombre], [descripcion]) VALUES (4, N'Dermatología', N'Servicio de Dermatología')
GO
INSERT [dbo].[servicio] ([id], [nombre], [descripcion]) VALUES (5, N'Ginecología', N'Servicio de atención médica especializado en salud femenina')
GO
SET IDENTITY_INSERT [dbo].[servicio] OFF
GO

-- Insertar datos en la tabla RoleServicio
INSERT [dbo].[RoleServicio] ([roleId], [servicioId]) VALUES (1004, 3)
GO
INSERT [dbo].[RoleServicio] ([roleId], [servicioId]) VALUES (1005, 4)
GO

SET IDENTITY_INSERT [dbo].[procedimiento] ON
GO
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1002, N'ecocardiograma traxtoraxica', 1, N'93306')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1003, N'monitoreo holter', 1, N'93224')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1004, N'Electrocardiograma', 1, N'93000')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1005, N'Endoscopia gastrointestinal alta', 3, N'43235')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1006, N'Biopsia de lesión de estómago', 3, N'43239')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1007, N'Ligadura de várices esofágicas', 3, N'91148')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1008, N'Escleroterapia de várices esofágicas con esofagoscopia', 3, N'43204')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1009, N'Proctosigmoidoscopia', 3, N'45300')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1010, N'Colonoscopia diagnóstica flexible', 3, N'45378')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1011, N'Colonoscopia con biopsia', 3, N'45380')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1012, N'Colocación de Sonda Nasoyeyunal', 3, N'45381')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1013, N'Extracción de cuerpo extraño', 3, N'91144')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1014, N'Hemostasia endoscópica alta', 3, N'91154')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1015, N'Videocolonoscopia izquierda', 3, N'45359')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1016, N'Polipectomia alta con videoendoscopio', 3, N'91142')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1017, N'Paracentesis diagnóstica', 3, N'49080')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1018, N'Paracentesis terapéutica', 3, N'91212')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1019, N'Kit de material médico para ligar varices esofágicas', 3, N'26317')
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1020, N'CRIO TERAPIA', 4, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1021, N'BIOPSIA DE PIEL', 4, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1022, N'ELECTRO CAUTERIZACIÓN', 4, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1023, N'INFILTRACIÓN INTRALESIONAL', 4, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1024, N'EXTRACCIÓN DE UNAS', 4, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1025, N'RETIRO DE PUNTOS', 4, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1026, N'EXTIRPACIÓN QUISTES', 4, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1027, N'General', 3, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1028, N'Microscopia binocular', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1029, N'endoscopia nasal', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1030, N'endoscopia faringolaringe', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1031, N'laringoscopia', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1032, N'reducción de fracturas nasales', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1033, N'drenaje de accesos O.N.G', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1034, N'retiro de cuerpos extraños O.N.G', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1035, N'Drenaje de hematomas', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1036, N'infiltracion intratimpanica', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1037, N'colección y retiro de tubo de ventilacion', 2, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1038, N'TEST NO ESTRESANTE', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1039, N'ECOGRAFIA FETAL DOPPLER', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1040, N'ECOGRAFIA OBSTETRICA SELECTIVA - EVALUACION MORFOLOGICA DEL FETO PARA DESCARTE DE MALFORMACIONES', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1041, N'ULTRASONIDO DE UTERO GRAVIDO EN EL PRIMER TRIMESTRE', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1042, N'ECOGRAFIA TRANSVAGINAL OBSTETRICA', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1043, N'ULTRASONIDO DE UTERO GRAVIDO, POSTERIOR AL 1ER TRIMESTRE', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1044, N'PERFIL BIOFISICO FETAL SIN TEST NO ESTRESANTE', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1045, N'ECOGRAFIA PELVICA', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1046, N'ECOGRAFIA TRANSVAGINAL (NO OBSTETRICA)', 5, NULL)
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1047, N'HISTEROSONOGRAFIA, CON
INSERT [dbo].[procedimiento] ([id], [nombre], [id_servicio], [codigo]) VALUES (1048, N'General', 2, NULL)


