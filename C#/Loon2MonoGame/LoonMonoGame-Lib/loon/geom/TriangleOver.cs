﻿using java.lang;

namespace loon.geom
{
    public class TriangleOver : Triangle
    {


        private readonly float[][] triangles;

        public TriangleOver(Triangle t)
        {
            triangles = JavaSystem.Dim<float>(t.GetTriangleCount() * 6 * 3, 2);

            int tcount = 0;
            for (int i = 0; i < t.GetTriangleCount(); i++)
            {
                float cx = 0;
                float cy = 0;
                for (int p = 0; p < 3; p++)
                {
                    float[] pt = t.GetTrianglePoint(i, p);
                    cx += pt[0];
                    cy += pt[1];
                }

                cx /= 3;
                cy /= 3;

                for (int p = 0; p < 3; p++)
                {
                    int n = p + 1;
                    if (n > 2)
                    {
                        n = 0;
                    }

                    float[] pt1 = t.GetTrianglePoint(i, p);
                    float[] pt2 = t.GetTrianglePoint(i, n);

                    pt1[0] = (pt1[0] + pt2[0]) / 2;
                    pt1[1] = (pt1[1] + pt2[1]) / 2;

                    triangles[(tcount * 3) + 0][0] = cx;
                    triangles[(tcount * 3) + 0][1] = cy;
                    triangles[(tcount * 3) + 1][0] = pt1[0];
                    triangles[(tcount * 3) + 1][1] = pt1[1];
                    triangles[(tcount * 3) + 2][0] = pt2[0];
                    triangles[(tcount * 3) + 2][1] = pt2[1];
                    tcount++;
                }

                for (int p = 0; p < 3; p++)
                {
                    int n = p + 1;
                    if (n > 2)
                    {
                        n = 0;
                    }

                    float[] pt1 = t.GetTrianglePoint(i, p);
                    float[] pt2 = t.GetTrianglePoint(i, n);

                    pt2[0] = (pt1[0] + pt2[0]) / 2;
                    pt2[1] = (pt1[1] + pt2[1]) / 2;

                    triangles[(tcount * 3) + 0][0] = cx;
                    triangles[(tcount * 3) + 0][1] = cy;
                    triangles[(tcount * 3) + 1][0] = pt1[0];
                    triangles[(tcount * 3) + 1][1] = pt1[1];
                    triangles[(tcount * 3) + 2][0] = pt2[0];
                    triangles[(tcount * 3) + 2][1] = pt2[1];
                    tcount++;
                }
            }
        }


        public void AddPolyPoint(float x, float y)
        {
        }


        public int GetTriangleCount()
        {
            return triangles.Length / 3;
        }


        public float[] GetTrianglePoint(int tri, int i)
        {
            float[] pt = triangles[(tri * 3) + i];

            return new float[] { pt[0], pt[1] };
        }


        public void StartHole()
        {
        }


        public bool Triangulate()
        {
            return true;
        }

    }

}
