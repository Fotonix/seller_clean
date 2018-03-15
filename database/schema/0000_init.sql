-- Drop schema.
DROP SCHEMA IF EXISTS public CASCADE;

-- Create schema.
CREATE SCHEMA public
    AUTHORIZATION seller;
GRANT ALL ON SCHEMA public TO seller;
COMMENT ON SCHEMA public IS 'Standard public schema';
