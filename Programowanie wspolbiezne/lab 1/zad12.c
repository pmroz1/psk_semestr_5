main(argc, argv, envp)
    int argc;
    char *argv[];
    char *envp[];
    {
    int ii;
    for (ii=0; envp[ii] != (char *) 0; ii++)
	    printf("%s\n",envp[ii]);
    exit(0);
}