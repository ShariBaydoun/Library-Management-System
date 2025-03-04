DO $$
BEGIN
    -- Check if the column exists
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns
                   WHERE table_name = 'book'
                   AND column_name = 'book_properties') THEN
        -- Add the column if it doesn't exist
        ALTER TABLE public.book ADD COLUMN book_properties jsonb NOT NULL DEFAULT '{}';
    END IF;
END $$;
